package kg.attractor.java.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import kg.attractor.java.data.LibraryData;
import kg.attractor.java.model.Employee;
import kg.attractor.java.model.LogEntry;
import kg.attractor.java.server.ResponseCodes;
import kg.attractor.java.template.RenderTemplate;
import kg.attractor.java.utils.CookieUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kg.attractor.java.template.RenderTemplate.sendErrorResponse;

public class LogRequestHandler implements HttpHandler {
    private final LibraryData dataService;

    public LogRequestHandler(LibraryData dataService) {
        this.dataService = dataService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String path = exchange.getRequestURI().getPath();

        if (!"/log".equals(path)) {
            sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "404 NOT FOUND");
            return;
        }
        try {
            String sessionId = CookieUtil.getUserIdFromCookie(exchange);
            List<LogEntry> logs = dataService.getLogs();
            Employee employee = dataService.getEmployeeById(sessionId);

            Map<String, Object> data = new HashMap<>();
            data.put("logs", logs);
            data.put("currentUser", employee);

            RenderTemplate.renderTemplate(exchange, "log.ftlh", data);
        } catch (IOException e) {
            e.printStackTrace();
            sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "Error loading log.");
        }
    }
}

