package kg.attractor.java.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import kg.attractor.java.data.LibraryData;
import kg.attractor.java.model.Employee;
import kg.attractor.java.template.RenderTemplate;

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
        Employee employee = createFakeUser();

        Map<String, Object> data = new HashMap<>();
        data.put("employee", employee);
        data.put("borrowedBooks", getBookTitles(employee.getBorrowedBooks()));
        data.put("pastBooks", getBookTitles(employee.getPastBooks()));

        RenderTemplate.renderTemplate(exchange, "profile.ftlh", data);
    }

    private List<String> getBookTitles(List<String> bookIds) {
        return bookIds.stream()
                .map(dataService::getBookById)
                .filter(book -> book != null)
                .map(book -> book.getTitle())
                .toList();
    }

    private Employee createFakeUser() {
        return new Employee("999", "Некий пользователь", "fake@email.com", "1234", List.of(), List.of());
    }
}
