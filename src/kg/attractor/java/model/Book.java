package kg.attractor.java.model;

public class Book {
    private String id;
    private String title;
    private String author;
    private boolean issued;
    private String borrowerId;
    private String image;

    public Book(String id, String title, String author, boolean issued) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = issued;
    }


    public String getBorrowerId() {
        return borrowerId;
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

