package com.example.ploc.controller;

import com.example.ploc.dto.LoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("login")
@Slf4j
public class AppController {
    HttpSession session;
    @GetMapping("/")
    public String home(HttpServletRequest request, Model model){
        boolean isSession = true;
        session = request.getSession(false);
        if(session == null) {
            isSession = false;
        }
        model.addAttribute("isSession", isSession);
        return "main";
    }
}
