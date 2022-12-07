package com.example.ploc.dto;

import com.example.ploc.domain.Login;
import com.example.ploc.domain.Teacher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherDTO {
    private Long id;
    private Login login;
    private String university;
    private String subject;

    public TeacherDTO(Teacher teacher)
    {
        this.id = teacher.getId();
        this.login = teacher.getLogin();
        this.university = teacher.getUniversity();
        this.subject = teacher.getSubject();
    }

    public TeacherDTO(Login login, String university, String subject) {
        this.login = login;
        this.university = university;
        this.subject = subject;
    }
}
