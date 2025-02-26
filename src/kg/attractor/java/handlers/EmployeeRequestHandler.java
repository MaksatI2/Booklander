package kg.attractor.java.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import kg.attractor.java.data.LibraryData;
import kg.attractor.java.model.Employee;
import kg.attractor.java.template.RenderTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeRequestHandler implements HttpHandler {
    private final LibraryData libraryData;

    public EmployeeRequestHandler(LibraryData libraryData) {
        this.libraryData = libraryData;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String[] parts = path.split("/");

        if (parts.length < 3) {
            RenderTemplate.sendErrorResponse(exchange, 400, "Некорректный запрос");
            return;
        }

        String employeeId = parts[2];
        Employee employee = libraryData.getEmployees().stream()
                .filter(e -> e.getId().equals(employeeId))
                .findFirst()
                .orElse(null);

        if (employee == null) {
            RenderTemplate.sendErrorResponse(exchange, 404, "Сотрудник не найден");
            return;
        }

        Map<String, Object> data = new HashMap<>();
        data.put("employee", employee);
        data.put("borrowedBooks", getBookTitles(employee.getBorrowedBooks()));
        data.put("pastBooks", getBookTitles(employee.getPastBooks()));

        RenderTemplate.renderTemplate(exchange, "employee.ftlh", data);
    }

    private List<String> getBookTitles(List<String> bookIds) {
        return bookIds.stream()
                .map(libraryData::getBookById)
                .filter(book -> book != null)
                .map(book -> book.getTitle())
                .toList();
    }
}
