package com.attend.liaproject.domain;


import lombok.Data;

@Data
public class Admin {
    private Long id;

    private String loginId;

    private String name;

    private String password;
}
