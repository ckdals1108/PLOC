package com.example.ploc.dto.login;

import com.example.ploc.domain.Identity;
import com.example.ploc.domain.Login;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.file.UploadFile;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginEditDTO {
    @NotBlank
    private String userId;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    private Identity identity;
    private String subject;
    private String university;
    private UploadFile attachFile;

    public LoginEditDTO(){

    }

    public LoginEditDTO(Login login)
    {
        this.userId = login.getUserId();
        this.password = login.getPassword();
        this.name = login.getName();
        this.identity = login.getIdentity();
    }

    public LoginEditDTO(Login login, Teacher teacher)
    {
        this.userId = login.getUserId();
        this.password = login.getPassword();
        this.name = login.getName();
        this.identity = login.getIdentity();
        this.university = teacher.getUniversity();
        this.subject = teacher.getSubject();
        attachFile = new UploadFile(teacher.getIdPhotoFile().getUpLoadFileName(), teacher.getIdPhotoFile().getStoreFileName());
    }
}
