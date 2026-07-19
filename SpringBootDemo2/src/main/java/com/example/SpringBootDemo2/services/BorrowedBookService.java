package com.example.SpringBootDemo2.services;

import com.example.SpringBootDemo2.DTO.BorrowRequestDTO;
import com.example.SpringBootDemo2.DTO.BorrowedBookByNameDTO;
import com.example.SpringBootDemo2.models.Book;
import com.example.SpringBootDemo2.models.BorrowedBook;
import com.example.SpringBootDemo2.models.Member;
import com.example.SpringBootDemo2.repositories.BookRepository;
import com.example.SpringBootDemo2.repositories.BorrowedBookRepository;
import com.example.SpringBootDemo2.repositories.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BorrowedBookService {

    @Autowired
    private BorrowedBookRepository borrowed_bookRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MemberRepository memberRepository;

    //LISTING ALL BORROWS
    public List<BorrowedBook> list() {
        log.info("All Borrowed Books Listed");
        return borrowed_bookRepository.findAll();
    }

    public BorrowedBook save(BorrowedBook borrow) {
        Book book = bookRepository.findById(borrow.getBook().getBook_id())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        borrow.setBook(book);

        Member member = memberRepository.findById(borrow.getMember().getMember_id())
                .orElseThrow(() -> new RuntimeException("Member Not Found"));
        borrow.setMember(member);
        log.info("Borrowed Book Saved");
        return borrowed_bookRepository.save(borrow);
    }

    //SEARCHING FOR A BORROW BY ID
    public BorrowedBook get(int borrow_id) {

        Optional<BorrowedBook> borrowOptional = borrowed_bookRepository.findById(borrow_id);
        if (borrowOptional.isEmpty()) {
            log.error("Borrowed Book Not Found for Borrow_ID: {}", borrow_id);
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND, "Book not found");
        }
        return borrowOptional.get();
    }

    public BorrowedBook update(int borrow_id, BorrowedBook borrow) {
        Optional<BorrowedBook> borrowOptional = borrowed_bookRepository.findById(borrow_id);
        if (borrowOptional.isEmpty()) {
            log.error("Book Not Found");
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND, "Borrow not found");
        }
        BorrowedBook existingBorrow = borrowOptional.get();
        existingBorrow.setBorrow_date(borrow.getBorrow_date());
        existingBorrow.setReturn_date(borrow.getReturn_date());
        existingBorrow.setBook(borrow.getBook());
        existingBorrow.setMember(borrow.getMember());
        log.info("Borrowed Book Updated");
        return borrowed_bookRepository.save(existingBorrow  );
    }

    //DELETING BOOKS
    public void delete(int borrow_id) {
        borrowed_bookRepository.deleteById(borrow_id);
        log.warn("Borrowed Book Deleted");
    }

    //LISTING ALL THE BOOKS A MEMBER BORROWED BY THEIR NAME
    public List<BorrowedBookByNameDTO> findBorrowedBooksByMemberName(String memberName) {
        log.info("Listed books that member '{}' borrowed.", memberName);
        return borrowed_bookRepository.findBorrowedBooksByMemberName(memberName);
    }

    //BORROWING BOOKS
    public BorrowedBook borrowBook(BorrowRequestDTO borrow) {
        Book book = bookRepository.findById(borrow.getBook_id())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Member member = memberRepository.findById(borrow.getMember_id())
                .orElseThrow(() -> new RuntimeException("Member Not Found"));

        BorrowedBook borrowedBook = new BorrowedBook(book,member,LocalDate.now(),null);
        book.setAvailable_copies(book.getAvailable_copies() -1);

        log.info("Borrowed Book Created");
        return borrowed_bookRepository.save(borrowedBook);
    }

    //RETURNING BOOKS
    public BorrowedBook returnBook(int borrow_id){
        Optional<BorrowedBook> borrowOptional = borrowed_bookRepository.findById(borrow_id);
        if (borrowOptional.isEmpty()) {
            log.error("Book Not Found");
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND, "Book not found");
        }

        BorrowedBook existingBorrow = borrowOptional.get();

        existingBorrow.setReturn_date(LocalDate.now());
        existingBorrow.getBook().setAvailable_copies(existingBorrow.getBook().getAvailable_copies() + 1);

        return borrowed_bookRepository.save(existingBorrow);
    }






}

