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
import java.util.Map;

import static kg.attractor.java.template.RenderTemplate.renderTemplate;
import static kg.attractor.java.template.RenderTemplate.sendErrorResponse;

public class BookRequestHandler implements HttpHandler {
    private final LibraryData dataService;

    public BookRequestHandler(LibraryData dataService) {
        this.dataService = dataService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String query = exchange.getRequestURI().getQuery();
        if (query == null || !query.startsWith("id=")) {
            sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "Invalid book ID.");
            return;
        }

        String[] params = query.split("&");
        String bookId = null;
        for (String param : params) {
            if (param.startsWith("id=")) {
                bookId = param.substring(3);
                break;
            }
        }

        if (bookId == null) {
            sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "Invalid book ID.");
            return;
        }

        String sessionId = CookieUtil.getUserIdFromCookie(exchange);
        Employee currentUser = (sessionId != null) ? dataService.getEmployeeById(sessionId) : null;

        Book book = dataService.getBookById(bookId);
        if (book == null) {
            sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "Book not found.");
            return;
        }

        Map<String, Object> data = new HashMap<>();
        String borrowLimit = null;
        if (query.contains("borrowLimit=exceeded")) {
            borrowLimit = "exceeded";
        }
        data.put("borrowLimit", borrowLimit);
        data.put("book", book);
        data.put("currentUser", currentUser);
        data.put("borrowerName", book.isIssued() ? dataService.getEmployeeNameById(book.getBorrowerId()) : "Не выдана");

        renderTemplate(exchange, "book.ftlh", data);
    }
}
