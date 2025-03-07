package kg.attractor.java.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import kg.attractor.java.data.LibraryData;
import kg.attractor.java.model.Book;
import kg.attractor.java.model.Employee;
import kg.attractor.java.server.ResponseCodes;
import kg.attractor.java.template.RenderTemplate;
import kg.attractor.java.utils.CookieUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kg.attractor.java.template.RenderTemplate.sendErrorResponse;

public class EmployeeRequestHandler implements HttpHandler {
    private final LibraryData libraryData;

    public EmployeeRequestHandler(LibraryData libraryData) {
        this.libraryData = libraryData;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String sessionId = CookieUtil.getUserIdFromCookie(exchange);
        try {
            String query = exchange.getRequestURI().getQuery();
            if (query == null || !query.startsWith("id=")) {
                RenderTemplate.sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "Invalid request: missing employee ID");
                return;
            }

            String employeeId = query.substring(3);
            Employee employee = libraryData.getEmployees().stream()
                    .filter(e -> e.getId().equals(employeeId))
                    .findFirst()
                    .orElse(null);

            if (employee == null) {
                RenderTemplate.sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "Employee not found");
                return;
            }

            Map<String, Object> data = new HashMap<>();
            Employee currentUser = libraryData.getEmployeeById(sessionId);
            data.put("employee", employee);

            List<Book> borrowedBooks = libraryData.getBooksByEmployee(employeeId);
            List<Book> pastBooks = libraryData.getPastBooksByEmployee(employeeId);

            data.put("borrowedBooks", borrowedBooks);
            data.put("pastBooks", pastBooks);
            data.put("currentUser", currentUser);

            RenderTemplate.renderTemplate(exchange, "employee.ftlh", data);
        } catch (Exception e) {
            e.printStackTrace();
            sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "Error loading employee.");
        }
    }


}
