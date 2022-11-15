package com.example.ploc.controller;

import com.example.ploc.dto.LoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("login")
@Slf4j
public class AppController {

    @GetMapping("/")
    public String home(){
        return "main";
    }

    @GetMapping("/test")
    public String test(@ModelAttribute("login") LoginDTO loginDTO){
        log.debug("login = {}",loginDTO.getLoginId());
        return "main";
    }
}
