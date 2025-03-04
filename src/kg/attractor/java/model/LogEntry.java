package kg.attractor.java.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LogEntry {
    private String bookName;
    private String employeeId;
    private String borrowDate;
    private String returnDate;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    public LogEntry(String bookName, String employeeId, LocalDate borrowDate) {
        this.bookName = bookName;
        this.employeeId = employeeId;
        this.borrowDate = borrowDate.format(formatter);
        this.returnDate = null;
    }

    public String getBookName() { return bookName; }
    public String getEmployeeId() { return employeeId; }
    public LocalDate getBorrowDate() { return LocalDate.parse(borrowDate, formatter); }
    public LocalDate getReturnDate() { return returnDate == null ? null : LocalDate.parse(returnDate, formatter); }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate.format(formatter);
    }
}
