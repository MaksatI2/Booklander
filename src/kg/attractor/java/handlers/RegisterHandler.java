package kg.attractor.java.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import kg.attractor.java.data.LibraryData;
import kg.attractor.java.template.RenderTemplate;
import kg.attractor.java.utils.FormParser;

import java.io.IOException;
import java.util.Map;

public class RegisterHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            RenderTemplate.renderTemplate(exchange, "register.ftlh", Map.of("message", ""));
        } else if ("POST".equals(exchange.getRequestMethod())) {
            handleRegistration(exchange);
        }
    }

    private void handleRegistration(HttpExchange exchange) throws IOException {
        Map<String, String> formData = FormParser.parse(exchange);
        String email = formData.get("email");
        String name = formData.get("name");
        String password = formData.get("password");

        if (LibraryData.getInstance().isUserExists(email)) {
            RenderTemplate.renderTemplate(exchange, "register.ftlh", Map.of("message", "Пользователь уже существует!"));
        } else {
            LibraryData.getInstance().addUser(email, name, password);
            RenderTemplate.renderTemplate(exchange, "register.ftlh", Map.of("message", "Регистрация успешна!"));
        }
    }
}
