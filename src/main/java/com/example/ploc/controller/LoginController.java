package com.example.ploc.controller;

import com.example.ploc.domain.Identity;
import com.example.ploc.domain.Login;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.LoginDTO;
import com.example.ploc.dto.LoginFormDTO;
import com.example.ploc.service.LoginService;
import com.example.ploc.service.TeacherService;
import com.example.ploc.service.web.validator.LoginValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final LoginService loginService;
    private final TeacherService teacherService;
    private final LoginValidator loginValidator;
    HttpSession session;

    @ModelAttribute("identityTypes")
    public Identity[] identityTypes() {
        return Identity.values();
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(defaultValue="/") String redirectURL,
                        @ModelAttribute LoginDTO loginDTO,
                        HttpServletRequest request){
        Login login = loginService.join(loginDTO);
        session = request.getSession();
        session.setAttribute("loginId", login.getId());
        return "redirect:" + redirectURL;
    }

    @GetMapping("/login/signup")
    public String signUpForm(Model model){
        model.addAttribute("login",new LoginFormDTO());
        model.addAttribute("id",null);
        return "signup";
    }

    @PostMapping("/login/signup")
    public String signUp(@Valid @ModelAttribute("login") LoginFormDTO loginFormDTO,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(loginFormDTO.getIdentity() == null){
            bindingResult.rejectValue("identity", "required");
        }

        if(loginFormDTO.getIdentity() == null || loginFormDTO.getIdentity().equals(Identity.TEACHER))
        {
            if(!StringUtils.hasText(loginFormDTO.getUniversity()) || !StringUtils.hasText(loginFormDTO.getSubject()))
            {
                loginValidator.validate(loginFormDTO, bindingResult);
            }
        }

        if (bindingResult.hasErrors()) {
            return "signup";
        }

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
        model.addAttribute("login",user);
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
