package com.attend.liaproject.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;
    private String name;
    private String birth;
    private String phone_num;
    private String sub_phone_num;
    private String address;
    private String gender;


}
