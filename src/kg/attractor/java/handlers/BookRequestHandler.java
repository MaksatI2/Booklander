package kg.attractor.java.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import kg.attractor.java.data.LibraryData;
import kg.attractor.java.template.RenderTemplate;
import kg.attractor.java.model.Book;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static kg.attractor.java.template.RenderTemplate.renderTemplate;

public class BookRequestHandler implements HttpHandler {
    private final LibraryData dataService;

    public BookRequestHandler(LibraryData dataService) {
        this.dataService = dataService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String bookId = exchange.getRequestURI().getPath().replace("/book/", "");
        Book book = dataService.getBookById(bookId);

        if (book == null) {
            RenderTemplate.sendErrorResponse(exchange, 404, "Book not found.");
            return;
        }

        Map<String, Object> data = new HashMap<>();
        data.put("book", book);
        data.put("borrowerName", book.isIssued() ? dataService.getEmployeeNameById(book.getBorrowerId()) : "Не выдана");

        renderTemplate(exchange, "book.ftlh", data);
    }
}

