package com.example.SpringBootDemo2.services;

import com.example.SpringBootDemo2.models.Member;
import com.example.SpringBootDemo2.repositories.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;
    @InjectMocks
    MemberService memberService;



    @Test
    void saveMemberSuccessfullyTest() {

        Member member = new Member("name1","example@email.com",LocalDate.now());
        when(memberRepository.save(member)).thenReturn(member);

        Member savedMember = memberService.save(member);

        assertNotNull(savedMember);
        assertEquals("name1",savedMember.getName());
        assertEquals("example@email.com",savedMember.getEmail());
        assertEquals(LocalDate.now(),savedMember.getJoin_date());


    }

    @Test
    void ListedMembersSuccessfullyTest() {

        List<Member> members = List.of(
                new Member("name1","example1@email.com",LocalDate.now()),
                new Member("name2","example2@email.com",LocalDate.now()));
        when(memberRepository.findAll()).thenReturn(members);

        List<Member> result = memberService.list();

        assertNotNull(result);
        assertEquals(members.size(),result.size());
        assertEquals(members.get(0).getName(),result.get(0).getName());
        assertEquals(members.get(1).getName(),result.get(1).getName());

    }

    @Test
    void getMemberByIdSuccessfullyTest() {
        Member member = new Member("name1","example@email.com",LocalDate.now());
        when(memberRepository.findById(1)).thenReturn(Optional.of(member));

        Member result = memberService.get(1);

        assertNotNull(result);
        assertEquals(member.getName(),result.getName());
        assertEquals(member.getEmail(),result.getEmail());
        assertEquals(member.getJoin_date(),result.getJoin_date());


    }

    @Test
    void updateMemberSuccessfullyTest() {

        Member member = new Member("name1","example@email.com",LocalDate.now());

        when(memberRepository.findById(1)).thenReturn(Optional.of(member));
        when(memberRepository.save(any(Member.class))).thenAnswer(i -> i.getArgument(0));

        Member updatedMember = memberService.update(1,member);

        assertNotNull(updatedMember);
        assertEquals(member.getName(),updatedMember.getName());
        assertEquals(member.getEmail(),updatedMember.getEmail());
        assertEquals(member.getJoin_date(),updatedMember.getJoin_date());

    }

    @Test
    void deleteMemberSuccessfullyTest() {
        doNothing().when(memberRepository).deleteById(1);
        memberService.delete(1);
        verify(memberRepository, times(1)).deleteById(1);


    }
}