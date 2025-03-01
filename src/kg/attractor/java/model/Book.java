package kg.attractor.java.model;

public class Book {
    private String id;
    private String title;
    private String author;
    private boolean issued;
    private String borrowerId;
    private String image;
    private String description;


    public Book(String id, String title, String author, boolean issued, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = issued;
        this.description = description;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }


    public String getBorrowerId() {
        return borrowerId;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return issued;
    }
}

