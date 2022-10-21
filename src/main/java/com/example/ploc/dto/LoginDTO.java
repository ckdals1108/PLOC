package com.example.ploc.dto;

import lombok.Getter;

@Getter
public class LoginDTO {
    private String loginId;
    private String password;

    public LoginDTO(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
