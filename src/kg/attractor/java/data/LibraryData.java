package kg.attractor.java.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import kg.attractor.java.model.Book;
import kg.attractor.java.model.Employee;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LibraryData {
    private static final String BOOKS_FILE_PATH = "data/Json/books.json";
    private static final String EMPLOYEES_FILE_PATH = "data/Json/employees.json";
    private static LibraryData instance;
    private List<Book> books;
    private List<Employee> employees;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public LibraryData() {
        loadData();
        try {
            Gson gson = new Gson();
            Type employeeListType = new TypeToken<List<Employee>>(){}.getType();
            employees = gson.fromJson(new FileReader(EMPLOYEES_FILE_PATH), employeeListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public List<Book> getBooksByEmployee(String employeeId) {
        return employees.stream()
                .filter(e -> e.getId().equals(employeeId))
                .flatMap(e -> e.getBorrowedBooks().stream())
                .map(this::getBookById)
                .toList();
    }

    public List<Book> getPastBooksByEmployee(String employeeId) {
        return employees.stream()
                .filter(e -> e.getId().equals(employeeId))
                .flatMap(e -> e.getPastBooks().stream())
                .map(this::getBookById)
                .toList();
    }

    public Employee getEmployeeBySession(String sessionId) {
        return employees.stream()
                .filter(e -> e.getId().equals(sessionId))
                .findFirst()
                .orElse(null);
    }

    public Employee getEmployeeById(String id) {
        return employees.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Employee getEmployeeByEmail(String email) {
        return employees.stream()
                .filter(e -> e.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public boolean isUserExists(String email) {
        return employees.stream().anyMatch(emp -> emp.getEmail().equals(email));
    }

    public void addUser(String email, String name, String password) {
        String newEmployeeId = generateNextEmployeeId();

        employees.add(new Employee(newEmployeeId, name, email, password, new ArrayList<>(), new ArrayList<>()));
        saveEmployees();
    }

    public String generateNextEmployeeId() {
        int maxId = employees.stream()
                .map(e -> e.getId())
                .mapToInt(Integer::parseInt)
                .max()
                .orElse(0);

        return String.valueOf(maxId + 1);
    }

    public boolean login(String email, String password) {
        Employee employee = getEmployeeByEmail(email);
        return employee != null && employee.getPassword().trim().equals(password.trim());
    }

    public void borrowBook(String userId, String bookId) {
        Employee employee = getEmployeeById(userId);
        Book book = getBookById(bookId);

        if (employee == null || book == null || book.isIssued()) {
            return;
        }

        book.setIssued(true);
        book.setBorrowerId(userId);
        employee.getBorrowedBooks().add(bookId);

        saveData();
    }

    public void returnBook(String userId, String bookId) {
        Employee employee = getEmployeeById(userId);
        Book book = getBookById(bookId);

        if (employee == null || book == null || !book.isIssued() || !book.getBorrowerId().equals(userId)) {
            return;
        }

        book.setIssued(false);
        book.setBorrowerId("");
        employee.getBorrowedBooks().remove(bookId);
        employee.getPastBooks().add(bookId);

        saveData();
    }

    public void saveData() {
        saveBooks();
        saveEmployees();
    }

    private void saveBooks() {
        try (FileWriter writer = new FileWriter(BOOKS_FILE_PATH)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(books, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveEmployees() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEES_FILE_PATH, StandardCharsets.UTF_8))) {
            writer.write(gson.toJson(employees));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


