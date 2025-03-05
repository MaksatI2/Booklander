package kg.attractor.java.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import kg.attractor.java.data.LibraryData;
import kg.attractor.java.model.Employee;
import kg.attractor.java.utils.CookieUtil;
import kg.attractor.java.utils.FormParser;

import java.io.IOException;
import java.util.Map;

public class BorrowBookHandler implements HttpHandler {
    private final LibraryData dataService;

    public BorrowBookHandler(LibraryData dataService) {
        this.dataService = dataService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            exchange.sendResponseHeaders(405, -1);
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

        if (bookId == null || bookId.isEmpty()) {
            exchange.sendResponseHeaders(400, -1);
            return;
        }

        Employee employee = dataService.getEmployeeById(userId);
        if (employee == null || employee.getBorrowedBooks() == null || employee.getBorrowedBooks().size() >= 2) {
            exchange.getResponseHeaders().set("Location", "/book?id=" + bookId + "&borrowLimit=exceeded");
            exchange.sendResponseHeaders(302, -1);
            return;
        }

        dataService.borrowBook(userId, bookId);
        exchange.getResponseHeaders().set("Location", "/profile");
        exchange.sendResponseHeaders(302, -1);
    }
}