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

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MatchController {
    private final TeacherService teacherService;

    @RequestMapping("/matchTable")
    public String matching(Model model){
        List<Teacher> teachers = teacherService.allTeacher();
        model.addAttribute("teacherList", teachers);
        return "matching";
    }

    @PostMapping("/chooseTeacher")
    public String match(@ModelAttribute("teacher") Teacher teacher, Model model){
        return "matching";
    }
}
