package kg.attractor.java;

import kg.attractor.java.server.LibraryServer;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        LibraryServer server = new LibraryServer(9889);
        server.start();
    }
}

