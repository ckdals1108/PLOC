package com.example.ploc.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String loginId;
    private String password;

    public LoginDTO(){}

    public LoginDTO(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
