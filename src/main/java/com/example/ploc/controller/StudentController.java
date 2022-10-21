package com.example.ploc.controller;

import com.example.ploc.domain.Student;
import com.example.ploc.dto.LoginDTO;
import com.example.ploc.dto.StudentDTO;
import com.example.ploc.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    HttpSession session;

    @GetMapping("/student/signup")
    public String signUpForm(){
        return "studentSignup";
    }

    @PostMapping("/student/signup")
    public String signUp(StudentDTO studentDto){
        Student student = new Student(studentDto.getLoginId(), studentDto.getPassword(), studentDto.getName(), studentDto.getSubject());
        studentService.create(student);
        return "redirect:/";
    }

    @RequestMapping("/login")
    public String login(LoginDTO loginDTO, HttpServletRequest request){
        Student signIn = ;
        try{
             signIn = studentService.join(loginDTO);
        }catch(Exception e)
        {

        }finally{
            session = request.getSession();
            System.out.println(signIn);
            if (signIn != null) {
                session.setAttribute("signIn", signIn);
                return "redirect:/";
            } else {
                session.setAttribute("signIn", null);
                return "redirect:/login";
            }
        }
    }

    @GetMapping("/logout")
    public String logout(){
        session.invalidate();
        return "redirect:/";
    }
}
