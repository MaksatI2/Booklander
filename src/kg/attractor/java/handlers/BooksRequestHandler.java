package kg.attractor.java.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import kg.attractor.java.data.LibraryData;
import kg.attractor.java.model.Book;
import kg.attractor.java.model.Employee;
import kg.attractor.java.server.ResponseCodes;
import kg.attractor.java.utils.CookieUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kg.attractor.java.template.RenderTemplate.renderTemplate;
import static kg.attractor.java.template.RenderTemplate.sendErrorResponse;

public class BooksRequestHandler implements HttpHandler {
    private final LibraryData dataService;

    public BooksRequestHandler(LibraryData dataService) {
        this.dataService = dataService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();

        if (!"/books".equals(path)) {
            sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "404 NOT FOUND");
            return;
        }

        String sessionId = CookieUtil.getUserIdFromCookie(exchange);
        try {
            List<Book> books = dataService.getBooks();
            Employee employee = dataService.getEmployeeById(sessionId);

            Map<String, Object> data = new HashMap<>();
            data.put("books", books);
            data.put("currentUser", employee);


            renderTemplate(exchange, "books.ftlh", data);
        } catch (Exception e) {
            e.printStackTrace();
            sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "Error loading books.");
        }
    }
}

