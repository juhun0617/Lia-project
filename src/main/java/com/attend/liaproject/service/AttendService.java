package com.attend.liaproject.service;

import com.attend.liaproject.entity.Attend;
import com.attend.liaproject.entity.Member;
import com.attend.liaproject.repository.AttendRepository;
import com.attend.liaproject.repository.MemberRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;

import javax.sound.sampled.FloatControl;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AttendService {

    @Autowired
    private AttendRepository attendRepository;

    @Autowired
    private MemberRepository memberRepository;

    public void attendsave(Attend attend) {
        attend.setTime(LocalDateTime.now());
        attendRepository.save(attend);
    }

    public List<Member> readAttenders() {
        List<Member> members = new ArrayList<>();

        LocalDate now = LocalDate.now();

        List<Attend> attenders = attendRepository.findAll();
        for (Attend attender : attenders) {

            if (attender.getTime()
                    .toLocalDate().equals(now)) {

                members.add(memberRepository.findByName(attender.getName())
                        .orElseThrow(NoSuchElementException::new));
            }
        }
        System.out.println(members);
        return members;
    }
    public List<Member> read_Not_Attenders() {
        List<Member> members = new ArrayList<>();
        List<Member> not_Attenders = new ArrayList<>();
        List<Member> all_member = memberRepository.findAll();
        LocalDate now = LocalDate.now();

        List<Attend> attenders = attendRepository.findAll();
        for (Attend attender : attenders) {

            if (attender.getTime()
                    .toLocalDate().equals(now)) {

                members.add(memberRepository.findByName(attender.getName())
                        .orElseThrow(NoSuchElementException::new));
            }
        }

        for (Member attend_member : all_member){
            if (members.contains(attend_member))
                continue;
            else {
                not_Attenders.add(attend_member);

            }

        }

        return not_Attenders;
    }



    public List<Attend> findMyChild(String name,String birth){
        Member member = memberRepository.findAll()
                .stream()
                .filter(s -> s.getName()
                        .equals(name) && s.getBirth()
                        .equals(birth))
                .findAny()
                .orElseThrow(NoSuchElementException::new);

        List<Attend> attend = attendRepository.findByName(member.getName());
        return attend;

    }


}
