package com.example.SpringBootDemo2.controllers;

import com.example.SpringBootDemo2.DTO.BorrowedBookDTO;
import com.example.SpringBootDemo2.models.Borrowed_Book;
import com.example.SpringBootDemo2.services.Borrowed_BookService;
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
@RequestMapping("borrow")
public class Borrowed_BookController {
    @Autowired
    Borrowed_BookService borrowed_bookService;

    @GetMapping(value = "/", produces = "application/json")
    public List<Borrowed_Book> getBorrowed_Books() {
        return borrowed_bookService.list();
    }

    @PostMapping(value = "/new", consumes = "application/json", produces = "application/json")
    public Borrowed_Book save(@RequestBody @Valid Borrowed_Book borrow) {
        return borrowed_bookService.save(borrow);
    }

    @GetMapping(value = "/id/{id}", produces = "application/json")
    public Borrowed_Book get(@PathVariable Long id) {
        return borrowed_bookService.get(id);
    }

    @PutMapping(value = "/id/{id}", consumes = "application/json", produces = "application/json")
    public Borrowed_Book update(@PathVariable Long id, @RequestBody @Valid Borrowed_Book book) {
        return borrowed_bookService.update(id, book);
    }

    @DeleteMapping(value = "/id/{id}", produces = "application/json")
    public void delete(@PathVariable Long id) {
        borrowed_bookService.delete(id);
    }

    @GetMapping(value = "/name/{name}", produces = "application/json")
    public List<BorrowedBookDTO> getByName(@PathVariable String name) {return borrowed_bookService.findBorrowedBooksByMemberName(name);}
}
