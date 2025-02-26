package kg.attractor.java;

import kg.attractor.java.server.LibraryServer;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new LibraryServer(9889).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
