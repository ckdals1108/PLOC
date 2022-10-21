package com.example.ploc.dto;

import lombok.Getter;

import javax.persistence.Column;

@Getter
public class TeacherDTO {
    private String loginId;
    private String password;
    private String name;
    private String universityName;
    private String subject;

    public TeacherDTO(String loginId, String password, String name, String universityName, String subject) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.universityName = universityName;
        this.subject = subject;
    }
}
