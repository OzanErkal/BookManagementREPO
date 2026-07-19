package com.example.SpringBootDemo2.services;

import com.example.SpringBootDemo2.DTO.BorrowRequestDTO;
import com.example.SpringBootDemo2.models.Book;
import com.example.SpringBootDemo2.models.BorrowedBook;
import com.example.SpringBootDemo2.models.Member;
import com.example.SpringBootDemo2.repositories.BookRepository;
import com.example.SpringBootDemo2.repositories.BorrowedBookRepository;
import com.example.SpringBootDemo2.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BorrowedBookServiceTest {

    @Mock
    BorrowedBookRepository borrowedBookRepository;
    @Mock
    MemberRepository memberRepository;
    @Mock
    BookRepository bookRepository;
    @InjectMocks
    BorrowedBookService borrowedBookService;

    private Book book;
    private Member member;


    @BeforeEach
    void setUp() {
        book = new Book("title1","author1",LocalDate.now(),"genre1",1);
        book.setBook_id(1);
        member = new Member("name1","example@email.com",LocalDate.now());
        member.setMember_id(1);
    }

    @Test
    void list() {
        Book book2 = new Book("title2","author2",LocalDate.now(),"genre2",1);
        Member member2 = new Member("name2","example2@email.com",LocalDate.now());
        List<BorrowedBook> borrowedBooks = List.of(
                new BorrowedBook(book,member,LocalDate.MIN,LocalDate.now()),
                new BorrowedBook(book2,member2,LocalDate.MIN,LocalDate.now())
        );

        when(borrowedBookRepository.findAll()).thenReturn(borrowedBooks);

        List<BorrowedBook> result = borrowedBookService.list();

        assertNotNull(result);
        assertEquals(borrowedBooks.size(),result.size());
        assertEquals(borrowedBooks,result);
        assertEquals(borrowedBooks,borrowedBookRepository.findAll());
        assertEquals("title2", result.get(1).getBook().getTitle());
        assertEquals("author2", result.get(1).getBook().getAuthor());
        assertEquals("genre2", result.get(1).getBook().getGenre());
        assertEquals(LocalDate.now(), result.get(1).getBook().getPublished_date());
        assertEquals(1,result.get(1).getBook().getAvailable_copies());



    }

    @Test
    void save() {
        BorrowedBook borrowedBook = new BorrowedBook(book,member,LocalDate.MIN, null);
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        when(memberRepository.findById(1)).thenReturn(Optional.of(member));
        when(borrowedBookRepository.save(any(BorrowedBook.class)))
                .thenAnswer(i -> i.getArguments()[0]);


        BorrowedBook result = borrowedBookService.save(borrowedBook);

        assertNotNull(result);
        assertEquals(book,result.getBook());
        assertEquals(member,result.getMember());
        assertEquals(LocalDate.now(),result.getBook().getPublished_date());
        assertNull(result.getReturn_date());


    }

    @Test
    void get() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteBorrowedBookSuccessfullyTest() {
        doNothing().when(borrowedBookRepository).deleteById(1);
        borrowedBookService.delete(1);
        verify(borrowedBookRepository,times(1)).deleteById(1);

    }

    @Test
    void findBorrowedBooksByMemberNameTest() {
    }

    @Test
    void borrowBook_createsBorrowedBookTest() {
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        when(memberRepository.findById(1)).thenReturn(Optional.of(member));
        when(borrowedBookRepository.save(any(BorrowedBook.class)))
                .thenAnswer(i -> i.getArgument(0));

        BorrowedBook result = borrowedBookService.borrowBook(new BorrowRequestDTO(1,1));

        assertEquals(book,result.getBook());
    }

    @Test
    void returnBook_setsReturnTest(){
        BorrowedBook borrowedBook = new  BorrowedBook(book,member,LocalDate.MIN,LocalDate.now());
        when(borrowedBookRepository.findById(1)).thenReturn(Optional.of(borrowedBook));
        when(borrowedBookRepository.save(any(BorrowedBook.class)))
                .thenAnswer(i -> i.getArgument(0));

        BorrowedBook result = borrowedBookService.returnBook(1);



        assertEquals(book,result.getBook());
        assertEquals(LocalDate.now(),result.getReturn_date());
        assertEquals(2,result.getBook().getAvailable_copies());

    }
}