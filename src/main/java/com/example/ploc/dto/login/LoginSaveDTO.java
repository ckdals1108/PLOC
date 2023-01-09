package com.example.ploc.dto.login;

import com.example.ploc.domain.Identity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginSaveDTO {
    @NotBlank
    private String userId;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    private Identity identity;
    private String subject;
    private String university;
}
