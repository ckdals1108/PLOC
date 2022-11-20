package com.example.ploc.controller;

import com.example.ploc.domain.Student;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.LoginDTO;
import com.example.ploc.service.StudentService;
import com.example.ploc.service.TeacherService;
import com.fasterxml.jackson.databind.deser.BasicDeserializerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final StudentService studentService;
    private final TeacherService teacherService;
    HttpSession session;

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDTO loginDTO, HttpServletRequest request){
        Object signIn = null;
        String identity = null;
        if (loginDTO != null) {
            Optional<Student> student = Optional.ofNullable(studentService.join(loginDTO));
            if (!student.isPresent()) {
                signIn = (Teacher) teacherService.join(loginDTO);
                identity = "teacher";

            } else {
                signIn = (Student)student.get();
                identity = "student";
            }
            session = request.getSession();
            session.setAttribute("login", signIn);
            session.setAttribute("identity", identity);
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/";
    }
}
