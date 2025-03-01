package kg.attractor.java.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import kg.attractor.java.data.LibraryData;
import kg.attractor.java.model.Book;
import kg.attractor.java.model.Employee;
import kg.attractor.java.template.RenderTemplate;
import kg.attractor.java.utils.CookieUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileRequestHandler implements HttpHandler {
    private final LibraryData dataService;

    public ProfileRequestHandler(LibraryData dataService) {
        this.dataService = dataService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String sessionId = CookieUtil.getUserIdFromCookie(exchange);

        if (sessionId == null) {
            exchange.getResponseHeaders().set("Location", "/login");
            exchange.sendResponseHeaders(302, -1);
            return;
        }

        Employee employee = dataService.getEmployeeById(sessionId);
        if (employee == null) {
            CookieUtil.clearUserIdCookie(exchange);
            exchange.getResponseHeaders().set("Location", "/login");
            exchange.sendResponseHeaders(302, -1);
            return;
        }

        Map<String, Object> data = new HashMap<>();
        data.put("employee", employee);
        data.put("borrowedBooks", getBookTitle(employee.getBorrowedBooks()));
        data.put("pastBooks", getBookTitle(employee.getPastBooks()));

        RenderTemplate.renderTemplate(exchange, "profile.ftlh", data);
    }

    private List<Book> getBookTitle(List<String> bookIds) {
        return bookIds.stream()
                .map(dataService::getBookById)
                .filter(book -> book != null)
                .toList();
    }
}
