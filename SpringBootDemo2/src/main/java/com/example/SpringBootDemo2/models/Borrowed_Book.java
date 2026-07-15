package com.example.SpringBootDemo2.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;

import java.util.Date;

@Entity
@Table(name = "Borrowed_Books")
public class Borrowed_Book {

    @Id
    @Column(name = "borrow_id")
    private int borrow_id;



    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Past
    @Column(name = "borrow_date")
    private Date borrow_date;

    @Column(name = "return_date")
    private Date return_date;

    public Borrowed_Book(Book book, Member member) {
        this.book = book;
        this.member = member;
    }

    public Borrowed_Book() {    }


    public int getBorrow_id() {
        return borrow_id;
    }

    public void setBorrow_id(int borrow_id) {
        this.borrow_id = borrow_id;
    }

    public Date getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(Date borrow_date) {
        this.borrow_date = borrow_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
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
