package com.example.SpringBootDemo2.services;
import com.example.SpringBootDemo2.models.Member;
import com.example.SpringBootDemo2.repositories.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> list() {
        log.info("All Members Listed");
        return memberRepository.findAll();
    }

    public Member save(Member member) {
        log.info("Member saved");
        return memberRepository.save(member);
    }

    public Member get(int member_id) {

        Optional<Member> memberOptional = memberRepository.findById(member_id);
        if (memberOptional.isEmpty()) {
            log.error("Member Not Found when getting for Member ID: {}", member_id);
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND, "member not found");
        }
        return memberOptional.get();
    }

    public Member update(int member_id, Member member) {
        Optional<Member> memberOptional = memberRepository.findById(member_id);
        if (memberOptional.isEmpty()) {
            log.error("Member Not Found when updating for Member ID: {}", member_id);
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND, "member not found");
        }
        Member existingmember = memberOptional.get();
        existingmember.setName(member.getName());
        existingmember.setEmail(member.getEmail());
        log.info("Member Updated");
        return memberRepository.save(existingmember);
    }

    public void delete(int member_id) {
        memberRepository.deleteById(member_id);
        log.warn("Member Deleted");
    }
}
