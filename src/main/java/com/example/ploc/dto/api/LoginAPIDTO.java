package com.example.ploc.dto.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginAPIDTO {
    private Long id;
    private String userId;
    private String name;
}
