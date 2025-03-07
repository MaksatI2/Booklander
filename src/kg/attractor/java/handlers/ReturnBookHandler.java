package kg.attractor.java.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import kg.attractor.java.data.LibraryData;
import kg.attractor.java.model.Employee;
import kg.attractor.java.server.ResponseCodes;
import kg.attractor.java.template.RenderTemplate;
import kg.attractor.java.utils.CookieUtil;
import kg.attractor.java.utils.FormParser;

import java.io.IOException;
import java.util.Map;

public class ReturnBookHandler implements HttpHandler {
    private final LibraryData dataService;

    public ReturnBookHandler(LibraryData dataService) {
        this.dataService = dataService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            RenderTemplate.sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "404 NOT FOUND");
            return;
        }

        String userId = CookieUtil.getUserIdFromCookie(exchange);
        if (userId == null) {
            exchange.getResponseHeaders().set("Location", "/login");
            exchange.sendResponseHeaders(302, -1);
            return;
        }

        Map<String, String> params = FormParser.parse(exchange);
        String bookId = params.get("bookId");

        Employee employee = dataService.getEmployeeById(userId);
        if (employee == null) {
            exchange.sendResponseHeaders(403, -1);
            return;
        }

        dataService.returnBook(userId, bookId);
        exchange.getResponseHeaders().set("Location", "/book?id=" + bookId + "&success=returned");
        exchange.sendResponseHeaders(302, -1);
    }
}
