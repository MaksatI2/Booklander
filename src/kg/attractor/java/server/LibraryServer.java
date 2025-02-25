package kg.attractor.java.server;

import com.sun.net.httpserver.HttpExchange;
import kg.attractor.java.data.LibraryData;
import kg.attractor.java.model.Book;
import kg.attractor.java.model.Employee;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kg.attractor.java.template.RenderTemplate.renderTemplate;

public class LibraryServer extends BasicServer {
    private final LibraryData dataService;

    public LibraryServer(String host, int port) throws IOException {
        super(host, port);
        this.dataService = LibraryData.getInstance();

        registerGet("/books", this::handleBooksRequest);
        registerGet("/book/1", this::handleBookRequest);
        registerGet("/employees", this::handleEmployeesRequest);
        registerGet("/employee/01", this::handleEmployeeRequest);
    }

    private void handleBooksRequest(HttpExchange exchange) {
        try {
            List<Book> books = dataService.getBooks();

            Map<String, Object> data = new HashMap<>();
            data.put("books", books);

            renderTemplate(exchange, "books.ftlh", data);
        } catch (Exception e) {
            e.printStackTrace();
            sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "Error loading books.");
        }
    }

    private void handleBookRequest(HttpExchange exchange) {
        try {
            String bookId = "1";

            Book book = dataService.getBookById(bookId);
            if (book == null) {
                sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "Book not found.");
                return;
            }

            String borrowerName = "Не выдана";
            if (book.isIssued() && book.getBorrowerId() != null) {
                borrowerName = dataService.getEmployeeNameById(book.getBorrowerId());
            }

            Map<String, Object> data = new HashMap<>();
            data.put("book", book);
            data.put("borrowerName", borrowerName);

            renderTemplate(exchange, "book.ftlh", data);
        } catch (Exception e) {
            e.printStackTrace();
            sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "Error loading book.");
        }
    }

    private void handleEmployeesRequest(HttpExchange exchange) {
        try {
            List<Employee> employees = dataService.getEmployees();

            Map<String, Object> data = new HashMap<>();
            data.put("employees", employees);

            Map<String, List<String>> borrowedBooksMap = new HashMap<>();
            for (Employee employee : employees) {
                borrowedBooksMap.put(employee.getId(), dataService.getBookTitlesByEmployee(employee.getId()));
            }
            data.put("borrowedBooksMap", borrowedBooksMap);

            Map<String, List<String>> pastBooksMap = new HashMap<>();
            for (Employee employee : employees) {
                pastBooksMap.put(employee.getId(), dataService.getPastBookTitlesByEmployee(employee.getId()));
            }
            data.put("pastBooksMap", pastBooksMap);

            renderTemplate(exchange, "employees.ftlh", data);
        } catch (Exception e) {
            e.printStackTrace();
            sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "Error loading employees.");
        }
    }

    private void handleEmployeeRequest(HttpExchange exchange) {
        try {
            String employeeId = "01";

            Employee employee = dataService.getEmployeeById(employeeId);

            if (employee == null) {
                sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "Employee not found.");
                return;
            }

            List<String> borrowedBooks = dataService.getBookTitlesByEmployee(employeeId);
            List<String> pastBooks = dataService.getPastBookTitlesByEmployee(employeeId);

            Map<String, Object> data = new HashMap<>();
            data.put("employee", employee);
            data.put("borrowedBooks", borrowedBooks);
            data.put("pastBooks", pastBooks);

            renderTemplate(exchange, "employee.ftlh", data);
        } catch (Exception e) {
            e.printStackTrace();
            sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "Error loading employee.");
        }
    }

    private void sendErrorResponse(HttpExchange exchange, ResponseCodes responseCode, String message) {
        try {
            byte[] data = message.getBytes();
            exchange.sendResponseHeaders(responseCode.getCode(), data.length);
            exchange.getResponseBody().write(data);
            exchange.getResponseBody().flush();
            exchange.getResponseBody().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

