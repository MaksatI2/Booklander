package kg.attractor.java.data;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import kg.attractor.java.model.Employee;
import kg.attractor.java.utils.CookieUtil;

import java.io.IOException;
import java.net.HttpCookie;
import java.util.List;
import java.util.Optional;

public class AuthFilter extends Filter {
    private final LibraryData dataService;

    public AuthFilter(LibraryData dataService) {
        this.dataService = dataService;
    }

    @Override
    public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
        List<String> cookies = exchange.getRequestHeaders().get("Cookie");

        if (cookies != null) {
            Optional<String> sessionCookie = cookies.stream()
                    .flatMap(c -> HttpCookie.parse(c).stream())
                    .filter(cookie -> "session_id".equals(cookie.getName()))
                    .map(HttpCookie::getValue)
                    .findFirst();

            if (sessionCookie.isPresent()) {
                String sessionId = sessionCookie.get();
                Employee employee = dataService.getEmployeeById(sessionId);

                if (employee != null) {
                    exchange.setAttribute("authenticatedUser", employee);
                    chain.doFilter(exchange);
                    return;
                }
            }
        }

        CookieUtil.clearUserIdCookie(exchange);
        exchange.getResponseHeaders().set("Location", "/login");
        exchange.sendResponseHeaders(302, -1);
    }

    @Override
    public String description() {
        return description();
    }
}

