package com.example.SpringBootDemo2.services;

import com.example.SpringBootDemo2.models.Book;
import com.example.SpringBootDemo2.models.Member;
import com.example.SpringBootDemo2.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> list() {
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book get(Long book_id) {

        Optional<Book> bookOptional = bookRepository.findById(book_id);
        if (bookOptional.isEmpty()) {
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND, "Book not found");
        }
        return bookOptional.get();
    }

    public Book update(Long book_id, Book book) {
        Optional<Book> bookOptional = bookRepository.findById(book_id);
        if (bookOptional.isEmpty()) {
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND, "Book not found");
        }
        Book existingBook = bookOptional.get();
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        return bookRepository.save(existingBook);
    }

    public void delete(Long book_id) {
        bookRepository.deleteById(book_id);
    }
}
