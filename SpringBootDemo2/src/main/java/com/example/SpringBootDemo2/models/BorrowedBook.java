package com.example.SpringBootDemo2.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "Borrowed_Books")
public class BorrowedBook {

    @Id
    @Column(name = "borrow_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int borrow_id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    
    @Column(name = "borrow_date")
    private LocalDate borrow_date;

    @Column(name = "return_date")
    private LocalDate return_date;

    public BorrowedBook(Book book, Member member, LocalDate borrow_date, LocalDate return_date) {
        this.book = book;
        this.member = member;
        this.borrow_date = borrow_date;
        this.return_date = return_date;
    }

    public BorrowedBook() {    }


}
