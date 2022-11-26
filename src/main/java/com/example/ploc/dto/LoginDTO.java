package com.example.ploc.dto;

import com.example.ploc.domain.Identity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String userId;
    private String password;

    public LoginDTO(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
