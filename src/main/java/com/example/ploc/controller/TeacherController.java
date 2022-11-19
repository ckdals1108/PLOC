package com.example.ploc.controller;

import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.LoginDTO;
import com.example.ploc.dto.TeacherDTO;
import com.example.ploc.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

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

    @RequestMapping("/teachers")
    public String teacherList(Model model){
        List<Teacher> teachers = teacherService.allTeacher();
        model.addAttribute("teacherList", teachers);
        return "teachers";
    }

    @GetMapping("/teacher")
    public String findById(@RequestParam Long id, Model model){
        model.addAttribute("teacher",teacherService.findById(id).get());
        return "teacher";
    }

}
