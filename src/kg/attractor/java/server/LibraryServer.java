package kg.attractor.java.server;

import com.sun.net.httpserver.HttpServer;
import kg.attractor.java.data.AuthFilter;
import kg.attractor.java.data.LibraryData;
import kg.attractor.java.handlers.*;
import kg.attractor.java.handlers.BooksRequestHandler;
import kg.attractor.java.handlers.BookRequestHandler;
import kg.attractor.java.handlers.EmployeesRequestHandler;
import kg.attractor.java.template.RenderTemplate;

import java.io.File;
import java.io.IOException;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Map;

import static kg.attractor.java.template.RenderTemplate.sendErrorResponse;

public class LibraryServer {
    private final HttpServer server;
    private final LibraryData dataService;

    public LibraryServer(int port) throws IOException {
        this.dataService = new LibraryData();
        this.server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/books", new BooksRequestHandler(dataService));
        server.createContext("/book/", new BookRequestHandler(dataService));
        server.createContext("/employees", new EmployeesRequestHandler(dataService));
        server.createContext("/employee", new EmployeeRequestHandler(dataService));
        server.createContext("/register", new RegisterHandler());
        server.createContext("/login", new LoginRequestHandler(dataService));
        server.createContext("/profile", new ProfileRequestHandler(dataService))
                .getFilters().add(new AuthFilter(dataService));
        server.createContext("/borrow", new BorrowBookHandler(dataService))
                .getFilters().add(new AuthFilter(dataService));
        server.createContext("/return", new ReturnBookHandler(dataService))
                .getFilters().add(new AuthFilter(dataService));


        server.createContext("/", exchange -> {
            String requestPath = exchange.getRequestURI().getPath();

            File file = new File("data" + requestPath);
            if (file.exists() && !file.isDirectory()) {
                new StaticFileHandler("data/").handle(exchange);
                return;
            }

            if ("/".equals(requestPath)) {
                RenderTemplate.renderTemplate(exchange, "root.html", Map.of());
                return;
            }

            RenderTemplate.sendErrorResponse(exchange, ResponseCodes.NOT_FOUND, "404 NOT FOUND");
        });



        server.setExecutor(null);
    }

    public void start() {
        server.start();
        System.out.println("Server started on port " + server.getAddress().getPort());
    }
}
