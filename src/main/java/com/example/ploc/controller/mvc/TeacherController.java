package com.example.ploc.controller.mvc;

import com.example.ploc.domain.Teacher;
import com.example.ploc.exception.DatabaseResultError;
import com.example.ploc.service.file.IdPhotoFileStore;
import com.example.ploc.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TeacherController {
    private final TeacherService teacherService;
    private final IdPhotoFileStore fileStore;

    @GetMapping("/member/teacher")
    public String teacherList(Model model){
        List<Teacher> teachers = null;
        try{
            teachers = teacherService.findAllWithLogin();
        }catch(DatabaseResultError e)
        {

        }
        model.addAttribute("teachers",teachers);
        return "teacherList";
    }

    @GetMapping("/member/teacher/{id}")
    public String teacher(@PathVariable Long id, Model model){
        Teacher teacher = teacherService.findByIdWithLogin(id);
        model.addAttribute("teacherLoginId", teacher.getLogin().getId());
        model.addAttribute("teacher", teacher);
        return "teacher";
    }
}
