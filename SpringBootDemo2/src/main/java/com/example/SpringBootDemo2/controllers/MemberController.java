package com.example.SpringBootDemo2.controllers;

import com.example.SpringBootDemo2.models.Member;

import com.example.SpringBootDemo2.services.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("members")
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping(value = "/", produces = "application/json")
    public List<Member> getMembers() {
        return memberService.list();
    }

    @PostMapping(value = "/new", consumes = "application/json", produces = "application/json")
    public Member save(@RequestBody @Valid Member member) {
        return memberService.save(member);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Member get(@PathVariable int id) {
        return memberService.get(id);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public Member update(@PathVariable int id, @RequestBody @Valid Member member) {
        return memberService.update(id, member);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void delete(@PathVariable int id) {
        memberService.delete(id);
    }
}

