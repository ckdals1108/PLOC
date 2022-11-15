package com.example.ploc.controller;

import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.LoginDTO;
import com.example.ploc.dto.TeacherDTO;
import com.example.ploc.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private HttpSession session;

    @GetMapping("/teacher/signup")
    public String signUpForm(){
        return "teacherSignup";
    }

    @PostMapping("/teacher/signup")
    public String signUp(@ModelAttribute TeacherDTO teacherDTO){
        Teacher teacher = new Teacher(teacherDTO.getLoginId(), teacherDTO.getPassword(), teacherDTO.getName(), teacherDTO.getUniversityName(), teacherDTO.getSubject());
        teacherService.create(teacher);
        return "redirect:/";
    }
}
