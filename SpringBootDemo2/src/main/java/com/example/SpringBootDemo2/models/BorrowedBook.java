package com.example.SpringBootDemo2.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

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

    @Past
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


    public int getBorrow_id() {
        return borrow_id;
    }

    public void setBorrow_id(int borrow_id) {
        this.borrow_id = borrow_id;
    }

    public LocalDate getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(LocalDate borrow_date) {
        this.borrow_date = borrow_date;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }


}
