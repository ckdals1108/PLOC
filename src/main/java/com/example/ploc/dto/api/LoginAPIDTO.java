package com.example.ploc.dto.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginAPIDTO {
    private Long id;
    private String userId;
    private String name;



    public LoginAPIDTO(Long id, String userId, String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }
}
