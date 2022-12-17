package com.example.ploc.service;

import com.example.ploc.domain.Identity;
import com.example.ploc.domain.Login;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.LoginDTO;
import com.example.ploc.dto.LoginFormDTO;
import com.example.ploc.repository.LoginRepository;
import com.example.ploc.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LoginService {
    private final LoginRepository loginRepository;
    private final TeacherRepository teacherRepository;

    public Login create(Login login){
        return loginRepository.save(login);
    }

    public Login join(LoginDTO login){
        Login database = loginRepository.findByUserId(login.getUserId());
        if(database.getPassword().equals(login.getPassword()))
            return database;
        else
            return null;
    }

    public Login findById(Long id){
        return loginRepository.findById(id);
    }

    public void withdrawal(Long id){
        Login login = loginRepository.findById(id);
        if(login.getIdentity().equals(Identity.STUDENT))
            loginRepository.remove(id);
        else if(login.getIdentity().equals(Identity.TEACHER))
            teacherRepository.remove(id);
    }

    public LoginFormDTO loginDetail(Long id){
        Login login = loginRepository.findById(id);
        if(login.getIdentity().equals(Identity.STUDENT))
        {
            return new LoginFormDTO(login);
        }
        else{
            Teacher teacher = teacherRepository.findWithLoginId(id);
            return new LoginFormDTO(login, teacher);
        }
    }

    public void loginEdit(Long id, LoginFormDTO loginFormDTO){
        Login login = loginRepository.findById(id);
        login.edit(loginFormDTO);
        if(loginFormDTO.getIdentity().equals(Identity.TEACHER))
        {
            Teacher teacher = teacherRepository.findWithLoginId(id);
            teacher.edit(loginFormDTO);
        }
    }
}
