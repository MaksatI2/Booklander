package kg.attractor.java.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import kg.attractor.java.data.LibraryData;
import kg.attractor.java.model.Employee;
import kg.attractor.java.server.ResponseCodes;
import kg.attractor.java.utils.CookieUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kg.attractor.java.template.RenderTemplate.renderTemplate;
import static kg.attractor.java.template.RenderTemplate.sendErrorResponse;

public class EmployeesRequestHandler implements HttpHandler {
    private final LibraryData dataService;

    public EmployeesRequestHandler(LibraryData dataService) {
        this.dataService = dataService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String sessionId = CookieUtil.getUserIdFromCookie(exchange);
        String path = exchange.getRequestURI().getPath();

        if (!"/employees".equals(path)) {
            sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "404 NOT FOUND");
            return;
        }

        try {
            List<Employee> employees = dataService.getEmployees();
            Employee employeeId = dataService.getEmployeeById(sessionId);


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
            data.put("currentUser", employeeId);

            renderTemplate(exchange, "employees.ftlh", data);
        }catch (Exception e) {
            e.printStackTrace();
            sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "Error loading employees.");
        }

    }
}
