package com.example.SpringBootDemo2.services;

import com.example.SpringBootDemo2.models.Book;
import com.example.SpringBootDemo2.repositories.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;


@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookRepository bookRepository;
    @InjectMocks
    BookService bookService;


    @Test
    void saveTest() {

        Book book = new Book();
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        book.setBook_id(1L);
        book.setTitle("Book 1");
        book.setAuthor("Author 1");
        book.setGenre("Genre 1");

        Book savedBook = bookService.save(book);

        Assertions.assertEquals(book.getBook_id(), savedBook.getBook_id());
        Assertions.assertEquals(book.getTitle(), savedBook.getTitle());
        Assertions.assertEquals(book.getAuthor(), savedBook.getAuthor());
        Assertions.assertEquals(book.getGenre(), savedBook.getGenre());

    }

    @Test
    void list() {
    }

    @Test
    void get() {

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}