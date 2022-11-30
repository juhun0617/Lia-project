package com.attend.liaproject.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "attend")
public class Attend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    private LocalDateTime time;

    // Entity setter를 지양해야하는 이유?
    // DTO(Data transfer object)  ?

}
