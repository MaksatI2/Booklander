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
        String sessionId = CookieUtil.getUserIdFromCookie(exchange);
        try {
            String path = exchange.getRequestURI().getPath();
            String bookId = path.substring(path.lastIndexOf("/") + 1);
            String userId = CookieUtil.getUserIdFromCookie(exchange);
            Employee currentUser = (userId != null) ? dataService.getEmployeeById(userId) : null;

            Book book = dataService.getBookById(bookId);
            Employee employee = dataService.getEmployeeById(sessionId);

            if (book == null) {
                sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "Book not found.");
                return;
            }

            Map<String, Object> data = new HashMap<>();
            boolean error = currentUser != null && currentUser.hasBorrowError();
            if (currentUser != null) {
                currentUser.setBorrowError(false);
            }
            data.put("book", book);
            data.put("currentUser", currentUser);
            data.put("borrowerName", book.isIssued() ? dataService.getEmployeeNameById(book.getBorrowerId()) : "Не выдана");
            data.put("currentUser", employee);
            data.put("error", error);

            renderTemplate(exchange, "book.ftlh", data);
        } catch (Exception e) {
            e.printStackTrace();
            sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "Error loading book.");
        }
    }
}
