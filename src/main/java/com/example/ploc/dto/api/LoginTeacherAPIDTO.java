package com.example.ploc.dto.api;

import com.example.ploc.domain.Identity;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginTeacherAPIDTO {
    private String userId;
    private String name;
}
