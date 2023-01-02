package com.example.ploc.controller.api;

import com.example.ploc.domain.Identity;
import com.example.ploc.domain.Login;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.LoginDTO;
import com.example.ploc.dto.LoginFormDTO;
import com.example.ploc.dto.api.LoginAPIDTO;
import com.example.ploc.dto.api.LoginAPIFormDTO;
import com.example.ploc.service.LoginService;
import com.example.ploc.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/login")
public class LoginApiController {
    private final LoginService loginService;
    private final TeacherService teacherService;

    @PostMapping("/signup")
    public String signupSave(@RequestBody LoginAPIFormDTO loginFormDTO){
        log.debug("login={}", loginFormDTO);
        if(loginFormDTO.getIdentity().equals(Identity.STUDENT)){
            Login login = new Login(loginFormDTO.getUserId(), loginFormDTO.getPassword(), loginFormDTO.getName(), loginFormDTO.getIdentity());
            loginService.create(login);
        }
        else {
            Login login = new Login(loginFormDTO.getUserId(), loginFormDTO.getPassword(), loginFormDTO.getName(), loginFormDTO.getIdentity());
            Teacher teacher = new Teacher(loginFormDTO.getSubject(), loginFormDTO.getUniversity(), login);
            teacherService.create(teacher);
        }
        return "success";
    }

    @PostMapping
    public ResponseEntity<LoginAPIDTO> loginOn(@RequestBody LoginDTO loginDTO){
        Login user = loginService.join(loginDTO);
        LoginAPIDTO login = new LoginAPIDTO(user.getId(), user.getUserId(), user.getName());
        return ResponseEntity.status(HttpStatus.OK)
                .body(login);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<LoginFormDTO> userStatus(@PathVariable Long id){
        LoginFormDTO loginFormDTO = loginService.loginDetail(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(loginFormDTO);
    }

    @PutMapping("/user/{id}")
    public String userEdit(@PathVariable Long id,
                           @RequestBody LoginFormDTO loginFormDTO){
        loginService.loginEdit(id, loginFormDTO);
        return "success";
    }

    @DeleteMapping("/user/{id}")
    public String withdrawal(@PathVariable Long id){
        loginService.withdrawal(id);
        return "success";
    }
}
