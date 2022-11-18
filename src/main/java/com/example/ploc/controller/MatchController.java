package com.example.ploc.controller;

import com.example.ploc.domain.Teacher;
import com.example.ploc.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MatchController {
    private final TeacherService teacherService;

    @RequestMapping("/teachers")
    public String matching(Model model){
        List<Teacher> teachers = teacherService.allTeacher();
        model.addAttribute("teacherList", teachers);
        return "teachers";
    }

    @PostMapping("/teacher")
    public String match(@RequestParam Long id, Model model){
        Optional<Teacher> teacher = teacherService.findById(id);

        return "teacher";
    }
}
