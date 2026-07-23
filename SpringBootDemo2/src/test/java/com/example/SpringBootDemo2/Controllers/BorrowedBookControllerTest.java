package com.example.SpringBootDemo2.Controllers;

import com.example.SpringBootDemo2.DTO.BorrowRequestDTO;
import com.example.SpringBootDemo2.DTO.BorrowedBookByNameDTO;
import com.example.SpringBootDemo2.models.Book;
import com.example.SpringBootDemo2.models.BorrowedBook;
import com.example.SpringBootDemo2.models.Member;
import com.example.SpringBootDemo2.services.BorrowedBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;


import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
class BorrowedBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private BorrowedBookService borrowed_bookService;

    private Book book;
    private Member member;
    private BorrowedBook borrowedBook;

    @BeforeEach
    void setUp() {
        book = new Book("title1", "author1", LocalDate.now().minusDays(1), "genre1", 3);
        book.setBook_id(1);

        member = new Member("name1","example@email.com",LocalDate.now().minusDays(1));
        member.setMember_id(1);

        borrowedBook = new BorrowedBook(book, member, LocalDate.now(), null);
    }

    @Test
    void getBorrowed_Books() {
    }

    @Test
    void save() {
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

    @Test
    void getByName() {
    }

    @Test
    void borrowBookSuccessfullyTest() throws Exception {
        when(borrowed_bookService.borrowBook(any(BorrowRequestDTO.class))).thenReturn(borrowedBook);

        String requestJson = """
            {
              "book_id": 1,
              "member_id": 1
            }
            """;

        mockMvc.perform(post("/borrowedBook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.book.book_id").value(1))
                .andExpect(jsonPath("$.member.member_id").value(1));

        verify(borrowed_bookService).borrowBook(any(BorrowRequestDTO.class));
    }

    @Test
    void returnBook() {
    }
}