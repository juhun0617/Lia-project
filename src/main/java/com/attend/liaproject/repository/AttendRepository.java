package com.attend.liaproject.repository;


import com.attend.liaproject.entity.Attend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AttendRepository extends JpaRepository<Attend, Integer> {
    List<Attend> findByName(String name);


    List<Attend> findByTime(LocalDateTime time);

}
