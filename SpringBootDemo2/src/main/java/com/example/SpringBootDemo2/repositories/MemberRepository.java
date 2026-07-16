package com.example.SpringBootDemo2.repositories;


import com.example.SpringBootDemo2.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

}