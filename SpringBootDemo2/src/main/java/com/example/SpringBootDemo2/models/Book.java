package com.example.SpringBootDemo2.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Books")
public class Book {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int book_id;
    @NotBlank
    @Column(name = "title")
    private String title;
    @NotBlank
    @Column(name = "author")
    private String author;
    @Past
    @Column(name = "published_date")
    private LocalDate published_date;
    @Column(name = "genre")
    private String genre;
    @Column(name = "available_copies")
    private int available_copies;


    public Book() {
    }

    public Book(String title, String author, LocalDate published_date, String genre, int available_copies) {
        this.title = title;
        this.author = author;
        this.published_date = published_date;
        this.genre = genre;
        this.available_copies = available_copies;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublished_date() {
        return published_date;
    }

    public void setPublished_date(LocalDate published_date) {
        this.published_date = published_date;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAvailable_copies() {
        return available_copies;
    }

    public void setAvailable_copies(int available_copies) {
        this.available_copies = available_copies;
    }

}