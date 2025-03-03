package kg.attractor.java.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import kg.attractor.java.data.LibraryData;
import kg.attractor.java.model.LogEntry;
import kg.attractor.java.template.RenderTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogRequestHandler implements HttpHandler {
    private final LibraryData dataService;

    public LogRequestHandler(LibraryData dataService) {
        this.dataService = dataService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        List<LogEntry> logs = dataService.getLogs();

        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("logs", logs);

        RenderTemplate.renderTemplate(exchange, "log.ftlh", dataModel);
    }
}

