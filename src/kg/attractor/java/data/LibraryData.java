package kg.attractor.java.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kg.attractor.java.model.Book;
import kg.attractor.java.model.Employee;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class LibraryData {
    private static final String BOOKS_FILE_PATH = "data/books.json";
    private static final String EMPLOYEES_FILE_PATH = "data/employees.json";
    private static LibraryData instance;
    private List<Book> books;
    private List<Employee> employees;

    private LibraryData() {
        loadData();
    }

    public static LibraryData getInstance() {
        if (instance == null) {
            instance = new LibraryData();
        }
        return instance;
    }

    private void loadData() {
        try (FileReader bookReader = new FileReader(BOOKS_FILE_PATH);
             FileReader employeeReader = new FileReader(EMPLOYEES_FILE_PATH)) {
            Gson gson = new Gson();
            Type bookListType = new TypeToken<List<Book>>() {
            }.getType();
            Type employeeListType = new TypeToken<List<Employee>>() {
            }.getType();

            books = gson.fromJson(bookReader, bookListType);
            employees = gson.fromJson(employeeReader, employeeListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getBooks() {
        return books;
    }
    public Book getBookById(String id) {
        return books.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Employee> getEmployees() {
        return employees;
    }
    public String getEmployeeNameById(String id) {
        return employees.stream().filter(e -> e.getId().equals(id)).map(Employee::getName).findFirst().orElse("Неизвестно");
    }

    public Employee getEmployeeById(String id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<String> getBookTitlesByEmployee(String employeeId) {
        return employees.stream()
                .filter(e -> e.getId().equals(employeeId))
                .flatMap(e -> e.getBorrowedBooks().stream())
                .map(this::getBookTitleById)
                .toList();
    }

    public List<String> getPastBookTitlesByEmployee(String employeeId) {
        return employees.stream()
                .filter(e -> e.getId().equals(employeeId))
                .flatMap(e -> e.getPastBooks().stream())
                .map(this::getBookTitleById)
                .toList();
    }

    private String getBookTitleById(String bookId) {
        return books.stream()
                .filter(b -> b.getId().equals(bookId))
                .map(Book::getTitle)
                .findFirst()
                .orElse("Неизвестная книга");
    }

}


