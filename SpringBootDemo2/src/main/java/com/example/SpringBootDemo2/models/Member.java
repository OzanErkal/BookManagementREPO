package com.example.SpringBootDemo2.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
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

    private LocalDate join_date;

    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    public Member(String name, String email, LocalDate join_date) {
        this.name = name;
        this.email = email;
        this.join_date = join_date;
    }

    public Member() {

    }


}
