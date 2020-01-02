package com.partum.books;

public class BookModel {
    private String authorName,bookTitle,creationDate,photoUrl;
    private int price;

    public BookModel() {
    }

    public BookModel(String authorName, String bookTitle, String creationDate, int price, String photoUrl) {
        this.authorName = authorName;
        this.bookTitle = bookTitle;
        this.creationDate = creationDate;
        this.price = price;
        this.photoUrl = photoUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public int getPrice() {
        return price;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
}
