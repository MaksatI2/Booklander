package kg.attractor.java.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import kg.attractor.java.data.LibraryData;
import kg.attractor.java.model.Employee;
import kg.attractor.java.template.RenderTemplate;
import kg.attractor.java.utils.CookieUtil;
import kg.attractor.java.utils.FormParser;

import java.io.IOException;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.Map;

public class LoginRequestHandler implements HttpHandler {
    private final LibraryData dataService;

    public LoginRequestHandler(LibraryData dataService) {
        this.dataService = dataService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String sessionId = CookieUtil.getUserIdFromCookie(exchange);
        String method = exchange.getRequestMethod();

        if (sessionId != null && LibraryData.getInstance().getEmployeeById(sessionId) != null) {
            exchange.getResponseHeaders().set("Location", "/profile");
            exchange.sendResponseHeaders(302, -1);
            return;
        }

        if ("GET".equals(method)) {
            RenderTemplate.renderTemplate(exchange, "login.ftlh", null);
        } else if ("POST".equals(method)) {
            Map<String, String> params = FormParser.parse(exchange);
            String email = params.get("email");
            String password = params.get("password");

            if (dataService.login(email, password)) {
                Employee employee = dataService.getEmployeeByEmail(email);

                String newSessionId = employee.getId();
                HttpCookie cookie = new HttpCookie("session_id", newSessionId);
                cookie.setMaxAge(600);
                cookie.setHttpOnly(true);

                exchange.getResponseHeaders().add("Set-Cookie", cookie.toString());

                Map<String, Object> data = new HashMap<>();
                data.put("employee", employee);
                data.put("borrowedBooks", employee.getBorrowedBooks().stream()
                        .map(dataService::getBookById)
                        .filter(book -> book != null)
                        .toList());

                data.put("pastBooks", employee.getPastBooks().stream()
                        .map(dataService::getBookById)
                        .filter(book -> book != null)
                        .toList());

                RenderTemplate.renderTemplate(exchange, "profile.ftlh", data);
            }
            else {
                Map<String, Object> data = new HashMap<>();
                data.put("errorMessage", "Ошибка входа: неверный email или пароль.");
                RenderTemplate.renderTemplate(exchange, "login.ftlh", data);
            }
        }
    }
}
