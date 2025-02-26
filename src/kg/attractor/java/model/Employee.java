package kg.attractor.java.model;

import java.util.List;

public class Employee {
    private String id;
    private String name;
    private String password;
    private List<String> borrowedBooks;
    private List<String> pastBooks;

    public Employee(String id, String name, String password, List<String> borrowedBooks, List<String> pastBooks) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.borrowedBooks = borrowedBooks;
        this.pastBooks = pastBooks;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getBorrowedBooks() {
        return borrowedBooks;
    }

    public List<String> getPastBooks() {
        return pastBooks;
    }
}
