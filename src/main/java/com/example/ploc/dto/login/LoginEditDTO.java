package com.example.ploc.dto.login;

import com.example.ploc.domain.Identity;
import com.example.ploc.domain.Login;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.file.UploadFile;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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
    private String upLoadFileName;
    private String storeFileName;
    private String filePath;
    private MultipartFile attachFile;

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
        if(teacher.getIdPhotoFile() != null){
            storeFileName = teacher.getIdPhotoFile().getStoreFileName();
            filePath = teacher.getIdPhotoFile().getFilePath();
            upLoadFileName = teacher.getIdPhotoFile().getUpLoadFileName();
        }
    }

    public Login getLogin(LoginEditDTO loginEditDTO){
        return new Login(loginEditDTO.getUserId(), loginEditDTO.getPassword(),loginEditDTO.getName(), loginEditDTO.getIdentity());
    }
}
