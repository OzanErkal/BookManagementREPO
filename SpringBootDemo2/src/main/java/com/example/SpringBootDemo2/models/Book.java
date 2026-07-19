package com.example.SpringBootDemo2.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
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
    @Column(name = "published_date")
    private LocalDate published_date;
    @Column(name = "genre")
    private String genre;
    @Column(name = "available_copies")
    private int available_copies;


    public Book() {
    }

    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    public Book(String title, String author, LocalDate published_date, String genre, int available_copies) {
        this.title = title;
        this.author = author;
        this.published_date = published_date;
        this.genre = genre;
        this.available_copies = available_copies;
    }

}