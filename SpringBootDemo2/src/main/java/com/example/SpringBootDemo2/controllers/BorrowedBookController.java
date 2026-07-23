package com.example.SpringBootDemo2.controllers;

import com.example.SpringBootDemo2.DTO.BorrowRequestDTO;
import com.example.SpringBootDemo2.DTO.BorrowedBookByNameDTO;
import com.example.SpringBootDemo2.models.BorrowedBook;
import com.example.SpringBootDemo2.services.BorrowedBookService;
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
@RequestMapping("borrowedBook")
public class BorrowedBookController {

    private final BorrowedBookService borrowed_bookService;

    public BorrowedBookController(BorrowedBookService borrowed_bookService) {
        this.borrowed_bookService = borrowed_bookService;
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<BorrowedBook> getBorrowed_Books() {
        return borrowed_bookService.list();
    }

    @PostMapping(value = "/new", consumes = "application/json", produces = "application/json")
    public BorrowedBook save(@RequestBody @Valid BorrowedBook borrow) {
        return borrowed_bookService.save(borrow);
    }

    @GetMapping(value = "/id/{id}", produces = "application/json")
    public BorrowedBook get(@PathVariable int id) {
        return borrowed_bookService.get(id);
    }

    @PutMapping(value = "/id/{id}", consumes = "application/json", produces = "application/json")
    public BorrowedBook update(@PathVariable int id, @RequestBody @Valid BorrowedBook book) {
        return borrowed_bookService.update(id, book);
    }

    @DeleteMapping(value = "/id/{id}", produces = "application/json")
    public void delete(@PathVariable int id) {
        borrowed_bookService.delete(id);
    }

    @GetMapping(value = "/name/{name}", produces = "application/json")
    public List<BorrowedBookByNameDTO> getByName(@PathVariable String name) {return borrowed_bookService.findBorrowedBooksByMemberName(name);}

    @PostMapping(value = "/borrow", produces = "application/json")
    public BorrowedBook borrowBook(@RequestBody @Valid BorrowRequestDTO borrowRequestDTO){return borrowed_bookService.borrowBook(borrowRequestDTO);}

    @PutMapping(value = "/return/{id}", produces = "application/json")
    public BorrowedBook returnBook(@PathVariable int id){return borrowed_bookService.returnBook(id);}
}
