package com.example.SpringBootDemo2.DTO;

public class BorrowedBookDTO {
    private final String bookTitle;
    private final String memberName;
    private final String author;

    public BorrowedBookDTO(String bookTitle, String memberName, String author) {
        this.bookTitle = bookTitle;
        this.memberName = memberName;
        this.author = author;
    }

    public String getBookTitle() { return bookTitle; }
    public String getMemberName() { return memberName; }
    public String getAuthor() { return author; }
}
