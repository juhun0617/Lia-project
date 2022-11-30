package com.attend.liaproject.repository;


import com.attend.liaproject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    List<Member> deleteMemberByName(String name);

    Optional<Member> findByName(String name);
}
