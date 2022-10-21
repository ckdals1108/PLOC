package com.example.ploc.controller;

import com.example.ploc.domain.Student;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.StudentDTO;
import com.example.ploc.dto.TeacherDTO;
import com.example.ploc.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/teacher/signup")
    public String signUpForm(){
        return "teacherSignup";
    }

    @PostMapping("/teacher/signup")
    public String signUp(TeacherDTO teacherDTO){
        Teacher teacher = new Teacher(teacherDTO.getLoginId(), teacherDTO.getPassword(), teacherDTO.getName(), teacherDTO.getUniversityName(), teacherDTO.getSubject());
        teacherService.create(teacher);
        return "redirect:/";
    }
}
