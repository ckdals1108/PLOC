package com.example.ploc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class StudentDTO {
    private String loginId;
    private String password;
    private String name;
    private String subject;

    public StudentDTO(String loginId, String password, String name, String subject) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.subject = subject;
    }
}
