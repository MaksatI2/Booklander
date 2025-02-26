package kg.attractor.java.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import kg.attractor.java.server.ResponseCodes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static kg.attractor.java.template.RenderTemplate.sendErrorResponse;

public class StaticFileHandler implements HttpHandler {
    private final String basePath;

    public StaticFileHandler(String basePath) {
        this.basePath = basePath;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String requestPath = exchange.getRequestURI().getPath();
            File file = new File(basePath + requestPath);

            if (!file.exists() || file.isDirectory()) {
                exchange.sendResponseHeaders( ResponseCodes.NOT_FOUND.getCode(), -1);
                return;
            }

            exchange.sendResponseHeaders(200, file.length());
            try (OutputStream os = exchange.getResponseBody()) {
                Files.copy(Path.of(file.getAbsolutePath()), os);
            }
        }catch (Exception e) {
            e.printStackTrace();
            sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "Error loading employee.");
        }
    }
}

