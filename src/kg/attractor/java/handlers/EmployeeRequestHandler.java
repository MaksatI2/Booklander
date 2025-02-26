package kg.attractor.java.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import kg.attractor.java.data.LibraryData;
import kg.attractor.java.model.Employee;
import kg.attractor.java.server.ResponseCodes;
import kg.attractor.java.template.RenderTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static kg.attractor.java.template.RenderTemplate.sendErrorResponse;

public class EmployeeRequestHandler implements HttpHandler {
    private final LibraryData libraryData;

    public EmployeeRequestHandler(LibraryData libraryData) {
        this.libraryData = libraryData;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String path = exchange.getRequestURI().getPath();
            String[] parts = path.split("/");

            if (parts.length < 3) {
                RenderTemplate.sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "Некорректный запрос");
                return;
            }

            String employeeId = parts[2];
            Employee employee = libraryData.getEmployees().stream()
                    .filter(e -> e.getId().equals(employeeId))
                    .findFirst()
                    .orElse(null);

            if (employee == null) {
                RenderTemplate.sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "Сотрудник не найден");
                return;
            }

            Map<String, Object> data = new HashMap<>();
            data.put("employee", employee);

            RenderTemplate.renderTemplate(exchange, "employee.ftlh", data);
        } catch (Exception e) {
            e.printStackTrace();
            sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "Error loading employee.");
        }
    }

}
