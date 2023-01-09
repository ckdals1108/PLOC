package com.example.ploc.controller.api;

import com.example.ploc.domain.Identity;
import com.example.ploc.domain.Login;
import com.example.ploc.dto.api.LoginAPIDTO;
import com.example.ploc.dto.login.LoginDTO;
import com.example.ploc.dto.login.LoginEditDTO;
import com.example.ploc.dto.login.LoginFormDTO;
import com.example.ploc.exception.UserException;
import com.example.ploc.service.LoginService;
import com.example.ploc.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/login")
public class LoginApiController {
    private final LoginService loginService;
    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<LoginAPIDTO> loginOn(@RequestBody LoginDTO loginDTO){
        Login user = loginService.join(loginDTO);
        LoginAPIDTO login = new LoginAPIDTO(user.getId(), user.getUserId(), user.getName());
        return ResponseEntity.status(HttpStatus.OK)
                .body(login);
    }

    @PostMapping("/signup")
    public String signupSave(@RequestBody LoginFormDTO loginFormDTO) throws IOException {
        log.debug("login={}", loginFormDTO);
        if(loginFormDTO.getIdentity().equals(Identity.STUDENT))
            loginService.create(loginFormDTO);
        else
            teacherService.create(loginFormDTO);
         return "success";
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<LoginEditDTO> userStatus(@PathVariable Long id){
        LoginEditDTO loginFormDTO = loginService.loginDetail(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(loginFormDTO);
    }

    @PutMapping("/user/{id}")
    public String userEdit(@PathVariable Long id,
                           @RequestBody LoginEditDTO loginEditDTO) throws IOException {
        if(loginEditDTO.getIdentity() == Identity.STUDENT)
            loginService.edit(id, loginEditDTO);
        else
            teacherService.edit(id, loginEditDTO, null, false);
        return "success";
    }

    @DeleteMapping("/user/{id}")
    public String withdrawal(@PathVariable Long id){
        loginService.withdrawal(id);
        return "success";
    }
}
