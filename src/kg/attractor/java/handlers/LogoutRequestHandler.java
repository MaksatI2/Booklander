package kg.attractor.java.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import kg.attractor.java.server.ResponseCodes;
import kg.attractor.java.utils.CookieUtil;

import java.io.IOException;

import static kg.attractor.java.template.RenderTemplate.sendErrorResponse;

public class LogoutRequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "404 NOT FOUND");
            return;
        }

        CookieUtil.clearUserIdCookie(exchange);

        exchange.getResponseHeaders().set("Location", "/login");
        exchange.sendResponseHeaders(302, -1);
    }
}

