package kg.attractor.java.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import kg.attractor.java.data.LibraryData;
import kg.attractor.java.model.Book;
import kg.attractor.java.server.ResponseCodes;

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
        try {
            String path = exchange.getRequestURI().getPath();
            String bookId = path.substring(path.lastIndexOf("/") + 1);

            Book book = dataService.getBookById(bookId);

            if (book == null) {
                sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "Book not found.");
                return;
            }

            Map<String, Object> data = new HashMap<>();
            data.put("book", book);
            data.put("borrowerName", book.isIssued() ? dataService.getEmployeeNameById(book.getBorrowerId()) : "Не выдана");

            renderTemplate(exchange, "book.ftlh", data);
        } catch (Exception e) {
            e.printStackTrace();
            sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "Error loading book.");
        }
    }
}
