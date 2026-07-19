package com.example.SpringBootDemo2.services;

import com.example.SpringBootDemo2.models.Book;
import com.example.SpringBootDemo2.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookRepository bookRepository;
    @InjectMocks
    BookService bookService;



    @Test
    void saveBookSuccessfullyTest() {

        Book book = new Book("title1","author1",LocalDate.now(),"genre1",1);
        when(bookRepository.save(book)).thenReturn(book);



        Book savedBook = bookService.save(book);

        assertEquals(book.getBook_id(), savedBook.getBook_id());
        assertEquals("title1", savedBook.getTitle());
        assertEquals("author1", savedBook.getAuthor());
        assertEquals("genre1", savedBook.getGenre());
        assertEquals(LocalDate.now(), savedBook.getPublished_date());

    }

    @Test
    void ListedBooksSuccessfullyTest() {
        List<Book> books = List.of(
                new Book("title1","author1",LocalDate.now(),"genre1",1),
                new Book("title2","author2",LocalDate.now(),"genre2",2));
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.list();


        assertNotNull(result);
        assertEquals(books.size(), result.size());
        assertEquals(books.get(0).getTitle(), result.get(0).getTitle());
        assertEquals(books.get(1).getTitle(), result.get(1).getTitle());
        verify(bookRepository, times(1)).findAll();




    }

    @Test
    void getBookByIdSuccessfullyTest() {
        Book book = new Book("title1","author1",LocalDate.now(),"genre1",1);
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        Book result = bookService.get(1);

        assertNotNull(result);
        assertEquals(book.getBook_id(), result.getBook_id());
        assertEquals(book.getTitle(), result.getTitle());
        assertEquals(book.getAuthor(), result.getAuthor());
        assertEquals(book.getGenre(), result.getGenre());
        assertEquals(book.getPublished_date(), result.getPublished_date());
        assertEquals(book.getAvailable_copies(), result.getAvailable_copies());
        verify(bookRepository, times(1)).findById(1);


    }

    @Test
    void updateBookSuccessfullyTest() {

        Book book = new Book("title1","author1",LocalDate.now(),"genre1",1);

        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenAnswer(i -> i.getArgument(0));

        Book updatedBook = bookService.update(1,book);

        assertEquals(book.getBook_id(), updatedBook.getBook_id());
        assertEquals(book.getTitle(), updatedBook.getTitle());
        assertEquals(book.getAuthor(), updatedBook.getAuthor());
        assertEquals(book.getGenre(), updatedBook.getGenre());
        assertEquals(book.getPublished_date(), updatedBook.getPublished_date());




    }

    @Test
    void deleteBookSuccessfullyTest() {
        doNothing().when(bookRepository).deleteById(1);
        bookService.delete(1);
        verify(bookRepository, times(1)).deleteById(1);


    }
}