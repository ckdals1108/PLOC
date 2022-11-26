package com.example.ploc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherDTO {
    private LoginDTO loginDTO;
    private String university;
    private String subject;

    public TeacherDTO(LoginDTO loginDTO, String university, String subject) {
        this.loginDTO = loginDTO;
        this.university = university;
        this.subject = subject;
    }
}
