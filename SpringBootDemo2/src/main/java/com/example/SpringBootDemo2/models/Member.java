package com.example.SpringBootDemo2.models;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int member_id;

    @NotBlank
    private String name;
    @Email
    private String email;
    @Past
    private LocalDate join_date;

    public Member(String name, String email, LocalDate join_date) {
        this.name = name;
        this.email = email;
        this.join_date = join_date;
    }

    public Member() {

    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getJoin_date() {
        return join_date;
    }

    public void setJoin_date(LocalDate join_date) {
        this.join_date = join_date;
    }



}
