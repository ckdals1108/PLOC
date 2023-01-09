package com.example.ploc.dto.login;

import com.example.ploc.domain.Identity;
import com.example.ploc.domain.Login;
import com.example.ploc.domain.Teacher;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginFormDTO {
    @NotBlank
    private String userId;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    private Identity identity;
    private String subject;
    private String university;
    private MultipartFile attachFile;

    public LoginFormDTO(){

    }

    public LoginFormDTO(String userId, String password, String name, Identity identity) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.identity = identity;
    }

    public LoginFormDTO(Login login)
    {
        this.userId = login.getUserId();
        this.password = login.getPassword();
        this.name = login.getName();
        this.identity = login.getIdentity();
    }

    public LoginFormDTO(Login login, Teacher teacher)
    {
        this.userId = login.getUserId();
        this.password = login.getPassword();
        this.name = login.getName();
        this.identity = login.getIdentity();
        this.university = teacher.getUniversity();
        this.subject = teacher.getSubject();
    }

    public LoginFormDTO(String userId, String password, String name, Identity identity, String subject, String university) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.identity = identity;
        this.subject = subject;
        this.university = university;
    }

    public LoginFormDTO(String userId, String password, String name, Identity identity, String subject, String university, MultipartFile attachFile) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.identity = identity;
        this.subject = subject;
        this.university = university;
        this.attachFile = attachFile;
    }

    public Login getLogin(LoginFormDTO loginFormDTO){
        return new Login(userId, password, name, identity);
    }

    public Teacher getTeacher(LoginFormDTO loginFormDTO){
        Login login = new Login(userId, password, name, identity);
        return new Teacher(subject, university, login);
    }

    @Override
    public String toString() {
        return "LoginFormDTO{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", identity=" + identity +
                ", subject='" + subject + '\'' +
                ", university='" + university + '\'' +
                '}';
    }
}
