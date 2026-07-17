package com.example.SpringBootDemo2.repositories;


import com.example.SpringBootDemo2.DTO.BorrowedBookDTO;
import com.example.SpringBootDemo2.models.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Integer> {
    List<BorrowedBook> findByMember_Name(String memberName);

    @Query("SELECT new com.example.SpringBootDemo2.DTO.BorrowedBookDTO(b.title, m.name, b.author) " +
            "FROM BorrowedBook l " +
            "JOIN l.member m " +
            "JOIN l.book b " +
            "WHERE m.name = :memberName")
    List<BorrowedBookDTO> findBorrowedBooksByMemberName(@Param("memberName") String memberName);
}