package com.example.SpringBootDemo2.services;

import com.example.SpringBootDemo2.DTO.BorrowedBookDTO;
import com.example.SpringBootDemo2.models.Book;
import com.example.SpringBootDemo2.models.Borrowed_Book;
import com.example.SpringBootDemo2.models.Member;
import com.example.SpringBootDemo2.repositories.BookRepository;
import com.example.SpringBootDemo2.repositories.Borrowed_BookRepository;
import com.example.SpringBootDemo2.repositories.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class Borrowed_BookService {

    @Autowired
    private Borrowed_BookRepository borrowed_bookRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MemberRepository memberRepository;

    public List<Borrowed_Book> list() {
        log.info("All Borrowed Books Listed");
        return borrowed_bookRepository.findAll();
    }

    public Borrowed_Book save(Borrowed_Book borrow) {
        Book book = bookRepository.findById(borrow.getBook().getBook_id())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        borrow.setBook(book);

        Member member = memberRepository.findById(borrow.getMember().getMember_id())
                .orElseThrow(() -> new RuntimeException("Member Not Found"));
        borrow.setMember(member);
        log.info("Borrowed Book Saved");
        return borrowed_bookRepository.save(borrow);
    }

    public Borrowed_Book get(Long borrow_id) {

        Optional<Borrowed_Book> borrowOptional = borrowed_bookRepository.findById(borrow_id);
        if (borrowOptional.isEmpty()) {
            log.error("Borrowed Book Not Found for Borrow_ID: {}", borrow_id);
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND, "Book not found");
        }
        return borrowOptional.get();
    }

    public Borrowed_Book update(Long borrow_id, Borrowed_Book borrow) {
        Optional<Borrowed_Book> borrowOptional = borrowed_bookRepository.findById(borrow_id);
        if (borrowOptional.isEmpty()) {
            log.error("Book Not Found");
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND, "Borrow not found");
        }
        Borrowed_Book existingBorrow = borrowOptional.get();
        existingBorrow.setBorrow_date(borrow.getBorrow_date());
        existingBorrow.setReturn_date(borrow.getReturn_date());
        existingBorrow.setBook(borrow.getBook());
        existingBorrow.setMember(borrow.getMember());
        log.info("Borrowed Book Updated");
        return borrowed_bookRepository.save(existingBorrow  );
    }

    public void delete(Long borrow_id) {
        borrowed_bookRepository.deleteById(borrow_id);
        log.warn("Borrowed Book Deleted");
    }

    public List<BorrowedBookDTO> findBorrowedBooksByMemberName(String memberName) {
        log.info("Listed books that member '{}' borrowed.", memberName);
        return borrowed_bookRepository.findBorrowedBooksByMemberName(memberName);
    }






}

