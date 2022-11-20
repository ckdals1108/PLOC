package com.example.ploc.controller;

import com.example.ploc.domain.Match;
import com.example.ploc.domain.Student;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.LoginDTO;
import com.example.ploc.dto.MatchTableFormDTO;
import com.example.ploc.service.MatchService;
import com.example.ploc.service.StudentService;
import com.example.ploc.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MatchController {
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final MatchService matchService;
    HttpSession session;

    @GetMapping("/matchingTableList")
    public String matchingTableListForm(Model model, HttpServletRequest request){
        session = request.getSession(false);
        Student student = (Student)session.getAttribute("login");
        List<MatchTableFormDTO> matchList = matchService.findByStudent(student);
        model.addAttribute("matchList", matchList);
        return "matchingTableList";
    }

    @PostMapping("/matchingTableList")
    public String matchingTableList(@RequestParam("type") String type,
                                    @RequestParam("search") String value,
                                    Model model,
                                    HttpServletRequest request){
        log.debug("type={} search={}",type,value);
        session = request.getSession(false);
        Student student = (Student)session.getAttribute("login");
        List<MatchTableFormDTO> matchList = matchService.findByStudentWithType(student, type, value);
        model.addAttribute("matchList", matchList);
        return "matchingTableList";
    }

    @GetMapping("/matchingTableBoard")
    public String matchingTableBoard(Model model){
        return "matchingTableBoard";
    }

    @GetMapping("/teacher/matchingTable")
    public String matchingTableForm(@RequestParam long id, Model model){
        model.addAttribute(teacherService.findById(id).get());
        return "matchingTable";
    }

    @PostMapping("/teacher/matchingTable")
    public String matchingTable(@RequestParam Long id,
                                @RequestParam int wageOfWeek,
                                @RequestParam int dayOfWeek,
                                HttpServletRequest request,
                                Model model){
        session = request.getSession(false);
        Student student = (Student)session.getAttribute("login");
        Teacher teacher = teacherService.findById(id).get();
        Match match = matchService.match(student, teacher, wageOfWeek, dayOfWeek);
        MatchTableFormDTO matchTableDTO = new MatchTableFormDTO(match, teacher);
        model.addAttribute("matchTableDTO", matchTableDTO);
        return "/matchingTableBoard";
    }
}
