package kg.attractor.java.server;

import com.sun.net.httpserver.HttpServer;
import kg.attractor.java.data.LibraryData;
import kg.attractor.java.handlers.*;
import kg.attractor.java.handlers.BooksRequestHandler;
import kg.attractor.java.handlers.BookRequestHandler;
import kg.attractor.java.handlers.EmployeesRequestHandler;

import java.io.IOException;

import java.net.InetSocketAddress;

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
        server.createContext("/", new StaticFileHandler("data/"));

        server.setExecutor(null);
    }
    public void start() {
        server.start();
        System.out.println("Server started on port " + server.getAddress().getPort());
    }

}
