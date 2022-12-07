package com.example.ploc.controller;

import com.example.ploc.domain.Identity;
import com.example.ploc.domain.Login;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.LoginDTO;
import com.example.ploc.dto.LoginFormDTO;
import com.example.ploc.repository.LoginRepository;
import com.example.ploc.service.LoginService;
import com.example.ploc.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final LoginService loginService;
    private final TeacherService teacherService;
    HttpSession session;
    Long id;

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDTO loginDTO,
                        HttpServletRequest request){
        Login login = loginService.join(loginDTO);
        session = request.getSession();
        session.setAttribute("loginId", login.getId());
        return "redirect:/";
    }

    @GetMapping("/login/signup")
    public String signUpForm(){
        return "signup";
    }

    @PostMapping("/login/signup")
    public String signUp(@ModelAttribute LoginFormDTO loginFormDTO){
        if(loginFormDTO.getIdentity().equals(Identity.STUDENT)) {
            Login login = loginService.create(loginFormDTO.getLogin(loginFormDTO));
        }
        else {
            Teacher login = teacherService.create(loginFormDTO.getTeacher(loginFormDTO));
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/";
    }

    @GetMapping("/withdrawal")
    public String withdrawal(HttpServletRequest request){
        session = request.getSession(false);
        Long loginId = (Long)session.getAttribute("loginId");
        loginService.withdrawal(loginId);
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/login/edit")
    public String editForm(HttpServletRequest request,
                         Model model)
    {
        Long loginId = (Long)request.getSession(false).getAttribute("loginId");
        LoginFormDTO user = loginService.loginDetail(loginId);
        model.addAttribute("user",user);
        model.addAttribute("id",loginId);
        return "signup";
    }

    @PostMapping("/login/edit")
    public String edit(@ModelAttribute LoginFormDTO loginFormDTO,
                       HttpSession session,
                       Model model){
        Long loginId = (Long)session.getAttribute("loginId");
        loginService.loginEdit(loginId, loginFormDTO);
        return "redirect:/";
    }
}
