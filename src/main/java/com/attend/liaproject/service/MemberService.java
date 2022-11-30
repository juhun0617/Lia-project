package com.attend.liaproject.service;


import com.attend.liaproject.entity.Member;
import com.attend.liaproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public void registration(Member member) {
        memberRepository.save(member);
    }


    public List<Member> memberList() {
        return memberRepository.findAll();
    }

    public void delete(Member member) {
        memberRepository.delete(member);
    }


    @Transactional
    public Member findChild(String name, String birth) {

        Member member = memberRepository.findAll()
                .stream()
                .filter(s -> s.getName()
                        .equals(name) && s.getBirth()
                        .equals(birth))
                .findAny()
                .orElseThrow(NoSuchElementException::new);


        return member;
    }

    @Transactional
    public void memberdelete(String name) {
        memberRepository.deleteMemberByName(name);
    }

}
