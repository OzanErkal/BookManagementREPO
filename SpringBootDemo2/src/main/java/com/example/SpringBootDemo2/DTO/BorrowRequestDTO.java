package com.example.SpringBootDemo2.DTO;

public class BorrowRequestDTO {
    private final int book_id;
    private final int member_id;

    public BorrowRequestDTO(int book_id, int member_id) {
        this.book_id = book_id;
        this.member_id = member_id;
    }

    public int getBook_id() {
        return book_id;
    }
    public int getMember_id() {
        return member_id;
    }
}
