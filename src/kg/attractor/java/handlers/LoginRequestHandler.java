package kg.attractor.java.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import kg.attractor.java.data.LibraryData;
import kg.attractor.java.server.ResponseCodes;
import kg.attractor.java.template.RenderTemplate;
import kg.attractor.java.utils.FormParser;

import java.io.IOException;
import java.util.HashMap;
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
                String redirectUrl = "/profile?email=" + email;
                exchange.getResponseHeaders().set("Location", redirectUrl);
                exchange.sendResponseHeaders(ResponseCodes.REDIRECT.getCode(), 0);
            } else {
                Map<String, Object> data = new HashMap<>();
                data.put("errorMessage", "Ошибка входа: неверный email или пароль.");
                RenderTemplate.renderTemplate(exchange, "login.ftlh", data);
            }
        }
    }
}
