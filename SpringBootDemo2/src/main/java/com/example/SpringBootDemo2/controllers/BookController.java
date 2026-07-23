package com.example.SpringBootDemo2.controllers;

import com.example.SpringBootDemo2.models.Book;
import com.example.SpringBootDemo2.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<Book> getBooks() {
        return bookService.list();
    }

    @PostMapping(value = "/new", consumes = "application/json", produces = "application/json")
    public Book save(@RequestBody @Valid Book book) {
        return bookService.save(book);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Book get(@PathVariable int id) {
        return bookService.get(id);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public Book update(@PathVariable int id, @RequestBody @Valid Book book) {
        return bookService.update(id, book);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void delete(@PathVariable int id) {
        bookService.delete(id);
    }
}
