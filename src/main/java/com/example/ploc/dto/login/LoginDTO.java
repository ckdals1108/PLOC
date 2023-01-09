package com.example.ploc.dto.login;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginDTO {
    @NotBlank
    private String userId;
    @NotBlank
    private String password;

    protected LoginDTO(){

    }

    public LoginDTO(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
