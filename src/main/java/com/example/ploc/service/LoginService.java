package com.example.ploc.service;

import com.example.ploc.domain.Identity;
import com.example.ploc.domain.Login;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.LoginDTO;
import com.example.ploc.dto.LoginEditDTO;
import com.example.ploc.dto.LoginFormDTO;
import com.example.ploc.exception.UserException;
import com.example.ploc.repository.LoginRepository;
import com.example.ploc.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LoginService {
    private final LoginRepository loginRepository;
    private final TeacherRepository teacherRepository;

    public Login create(Login login){
        List list = loginRepository.findByUserId(login.getUserId());
        if(!list.isEmpty()){
            throw new UserException("중복된 회원이 있습니다.");
        }

        return loginRepository.save(login);
    }

    public Login join(LoginDTO login){
        Login database;

        List list = loginRepository.findByUserId(login.getUserId());

        if(list.isEmpty())
            throw new UserException("아이디 또는 비밀번호를 잘못 입력하셨습니다.");
        else
            database = (Login)list.get(0);

        if(!database.getPassword().equals(login.getPassword()))
            throw new UserException("아이디 또는 비밀번호를 잘못 입력하셨습니다.");

        return database;
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

    public void withdrawal(LoginDTO loginDTO){
        Login database;
        List list = loginRepository.findByUserId(loginDTO.getUserId());

        if(!list.isEmpty()) {
            database = (Login)list.get(0);
            if(database.getIdentity().equals(Identity.STUDENT))
                loginRepository.remove(database.getId());
            else if(database.getIdentity().equals(Identity.TEACHER))
                teacherRepository.remove(database.getId());
        }
    }

    public LoginEditDTO loginDetail(Long id){
        Login login = loginRepository.findById(id);
        if(login.getIdentity().equals(Identity.STUDENT))
        {
            return new LoginEditDTO(login);
        }
        else{
            Teacher teacher = teacherRepository.findWithLoginId(id);
            return new LoginEditDTO(login, teacher);
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
