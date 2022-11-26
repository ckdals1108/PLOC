package com.example.ploc.controller;


import com.example.ploc.domain.Login;
import com.example.ploc.domain.Match;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.LoginDTO;
import com.example.ploc.dto.MatchFormDTO;
import com.example.ploc.dto.MatchTableBoardDTO;
import com.example.ploc.dto.MatchTableBoardListDTO;
import com.example.ploc.repository.LoginRepository;
import com.example.ploc.service.LoginService;
import com.example.ploc.service.MatchService;
import com.example.ploc.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MatchController {
    private final MatchService matchService;
    private final TeacherService teacherService;
    private final LoginService loginService;
    HttpSession session;

    @GetMapping("/matching/{id}")
    public String matchForm(@PathVariable Long id,
                        Model model){
        Teacher teacher = teacherService.findWithId(id);
        model.addAttribute("teacher", teacher);
        return "matchingTable";
    }

    @PostMapping("/matching/{id}")
    public String match(@ModelAttribute MatchFormDTO matchFormDTO,
                        @PathVariable Long id,
                        HttpServletRequest request,
                        Model model)
    {
        session = request.getSession(false);
        Long studentId = (Long)(session.getAttribute("loginId"));
        Login login = loginService.findById(studentId);
        Teacher teacher = teacherService.findById(id);
        Match match = new Match(login, teacher, matchFormDTO.getWageOfDay(), matchFormDTO.getTimesOfWeek());
        Match matchTable = matchService.create(match);
        MatchTableBoardDTO matchBord = teacherService.findLogin(id);
        log.debug("teacherLogin={}",matchBord.toString());

        model.addAttribute("match", matchTable);
        model.addAttribute("teacher",matchBord);

        return "matchingTableBoard";
    }

    @GetMapping("/matching")
    public String matchListForm(Model model, HttpServletRequest request){
        session = request.getSession(false);
        Long studentId = (Long)session.getAttribute("loginId");
        List<MatchTableBoardListDTO> matches = matchService.matchList(studentId);
        log.debug("matches={}", matches.toString());
        model.addAttribute("matches", matches);
        return "matchingTableList";
    }

    @PostMapping("/matching")
    public String matchList(@RequestParam String search,
                            @RequestParam String type,
                            Model model, HttpServletRequest request){
        session = request.getSession(false);
        Long studentId = (Long)session.getAttribute("loginId");
        List<MatchTableBoardListDTO> matches = matchService.matchListWithType(studentId, type, search);
        model.addAttribute("matches", matches);
        return "matchingTableList";
    }
}
