package com.example.SpringBootDemo2.services;
import com.example.SpringBootDemo2.models.Member;
import com.example.SpringBootDemo2.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> list() {
        return memberRepository.findAll();
    }

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public Member get(Long member_id) {

        Optional<Member> memberOptional = memberRepository.findById(member_id);
        if (memberOptional.isEmpty()) {
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND, "member not found");
        }
        return memberOptional.get();
    }

    public Member update(Long member_id, Member member) {
        Optional<Member> memberOptional = memberRepository.findById(member_id);
        if (memberOptional.isEmpty()) {
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND, "member not found");
        }
        Member existingmember = memberOptional.get();
        existingmember.setName(member.getName());
        existingmember.setEmail(member.getEmail());
        return memberRepository.save(existingmember);
    }

    public void delete(Long member_id) {
        memberRepository.deleteById(member_id);
    }
}
