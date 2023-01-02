package com.example.ploc.dto.api;

import com.example.ploc.domain.Identity;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginAPIFormDTO {
    private String userId;
    private String password;
    private String name;
    private Identity identity;
    private String subject;
    private String university;

    public LoginAPIFormDTO() {
    }

    public LoginAPIFormDTO(String userId, String password, String name, Identity identity) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.identity = identity;
    }

    public LoginAPIFormDTO(String userId, String password, String name, Identity identity, String subject, String university) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.identity = identity;
        this.subject = subject;
        this.university = university;
    }

    @JsonValue
    public Identity getIdentity(){
        return identity;
    }
}
