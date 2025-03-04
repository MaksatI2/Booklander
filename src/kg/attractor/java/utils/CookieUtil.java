package kg.attractor.java.utils;

import com.sun.net.httpserver.HttpExchange;
import kg.attractor.java.server.Cookie;

import java.net.HttpCookie;
import java.util.List;
import java.util.Optional;

public class CookieUtil {
    private static final String COOKIE_NAME = "session_id";

    public static String getUserIdFromCookie(HttpExchange exchange) {
        List<String> cookies = exchange.getRequestHeaders().get("Cookie");
        if (cookies == null) {
            return null;
        }

        for (String cookieHeader : cookies) {
            List<HttpCookie> parsedCookies = HttpCookie.parse(cookieHeader);
            Optional<HttpCookie> userCookie = parsedCookies.stream()
                    .filter(cookie -> COOKIE_NAME.equals(cookie.getName()))
                    .findFirst();

            if (userCookie.isPresent()) {
                return userCookie.get().getValue();
            }
        }
        return null;
    }

    public static void clearUserIdCookie(HttpExchange exchange) {
        Cookie<String> expiredCookie = Cookie.make(COOKIE_NAME, "");
        expiredCookie.setMaxAge(0);
        expiredCookie.setHttpOnly(true);

        exchange.getResponseHeaders().add("Set-Cookie", expiredCookie.toString());
    }
}
