package com.example.ploc.dto;

import com.example.ploc.domain.Identity;
import com.example.ploc.domain.Login;
import com.example.ploc.domain.Teacher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginFormDTO {
    private String userId;
    private String password;
    private String name;
    private Identity identity;
    private String subject;
    private String university;

    public LoginFormDTO(String userId, String password, String name, Identity identity) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.identity = identity;
    }

    protected LoginFormDTO(){

    }

    public LoginFormDTO(String userId, String password, String name, Identity identity, String subject, String university) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.identity = identity;
        this.subject = subject;
        this.university = university;
    }

    public Login getLogin(LoginFormDTO loginFormDTO){
        return new Login(userId, password, name, identity);
    }

    public Teacher getTeacher(LoginFormDTO loginFormDTO){
        Login login = new Login(userId, password, name, identity);
        return new Teacher(subject, university, login);
    }
}
