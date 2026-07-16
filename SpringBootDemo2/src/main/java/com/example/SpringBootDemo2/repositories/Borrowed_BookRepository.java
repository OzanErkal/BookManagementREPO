package com.example.SpringBootDemo2.repositories;


import com.example.SpringBootDemo2.DTO.BorrowedBookDTO;
import com.example.SpringBootDemo2.models.Borrowed_Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Borrowed_BookRepository extends JpaRepository<Borrowed_Book, Integer> {
    List<Borrowed_Book> findByMember_Name(String memberName);

    @Query("SELECT new com.example.SpringBootDemo2.DTO.BorrowedBookDTO(b.title, m.name, b.author) " +
            "FROM Borrowed_Book l " +
            "JOIN l.member m " +
            "JOIN l.book b " +
            "WHERE m.name = :memberName")
    List<BorrowedBookDTO> findBorrowedBooksByMemberName(@Param("memberName") String memberName);
}