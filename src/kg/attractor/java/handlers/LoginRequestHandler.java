package kg.attractor.java.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import kg.attractor.java.data.LibraryData;
import kg.attractor.java.model.Employee;
import kg.attractor.java.template.RenderTemplate;
import kg.attractor.java.utils.FormParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginRequestHandler implements HttpHandler {
    private final LibraryData dataService;

    public LoginRequestHandler(LibraryData dataService) {
        this.dataService = dataService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();

        if ("GET".equals(method)) {
            RenderTemplate.renderTemplate(exchange, "login.ftlh", null);
        } else if ("POST".equals(method)) {
            Map<String, String> params = FormParser.parse(exchange);
            String email = params.get("email");
            String password = params.get("password");

            if (dataService.login(email, password)) {
                Employee employee = dataService.getEmployeeByEmail(email);

                Map<String, Object> data = new HashMap<>();
                data.put("employee", employee);
                data.put("borrowedBooks", getBookTitles(employee.getBorrowedBooks()));
                data.put("pastBooks", getBookTitles(employee.getPastBooks()));

                RenderTemplate.renderTemplate(exchange, "profile.ftlh", data);
            } else {
                Map<String, Object> data = new HashMap<>();
                data.put("errorMessage", "Ошибка входа: неверный email или пароль.");
                RenderTemplate.renderTemplate(exchange, "login.ftlh", data);
            }
        }
    }


    private List<String> getBookTitles(List<String> bookIds) {
        return bookIds.stream()
                .map(dataService::getBookById)
                .filter(book -> book != null)
                .map(book -> book.getTitle())
                .toList();
    }
}
