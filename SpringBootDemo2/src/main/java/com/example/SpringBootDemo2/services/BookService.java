package com.example.SpringBootDemo2.services;

import com.example.SpringBootDemo2.models.Book;
import com.example.SpringBootDemo2.repositories.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> list() {
        log.info("All Books Listed");
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        log.info("Book Saved");
        return bookRepository.save(book);
    }

    public Book get(int book_id) {

        Optional<Book> bookOptional = bookRepository.findById(book_id);
        if (bookOptional.isEmpty()) {
            log.error("Book not found when getting for id {}", book_id);
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND, "Book not found");
        }
        return bookOptional.get();
    }

    public Book update(int book_id, Book book) {
        Optional<Book> bookOptional = bookRepository.findById(book_id);
        if (bookOptional.isEmpty()) {
            log.error("Book not found when updating for id {}", book_id);
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND, "Book not found");
        }
        Book existingBook = bookOptional.get();
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPublished_date(book.getPublished_date());
        existingBook.setGenre(book.getGenre());
        existingBook.setAvailable_copies(book.getAvailable_copies());
        log.info("Book Updated");
        return bookRepository.save(existingBook);
    }

    public void delete(int book_id) {
        bookRepository.deleteById(book_id);
        log.warn("Book Deleted");
    }
}
