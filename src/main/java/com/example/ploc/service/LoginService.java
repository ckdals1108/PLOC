package com.example.ploc.service;

import com.example.ploc.domain.Identity;
import com.example.ploc.domain.Login;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.login.LoginDTO;
import com.example.ploc.dto.login.LoginEditDTO;
import com.example.ploc.dto.login.LoginFormDTO;
import com.example.ploc.exception.UserException;
import com.example.ploc.repository.LoginRepository;
import com.example.ploc.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LoginService {
    private final LoginRepository loginRepository;
    private final TeacherRepository teacherRepository;

    public Login create(LoginFormDTO loginFormDTO){
        Optional<Login> checkLogin = loginRepository.findWithUserId(loginFormDTO.getUserId());

        if(checkLogin.isPresent())
            throw new UserException("중복된 회원이 있습니다.");

        Login login = new Login(loginFormDTO.getUserId(), loginFormDTO.getPassword(), loginFormDTO.getName(), loginFormDTO.getIdentity());

        return loginRepository.save(login);
    }

    public Login join(LoginDTO login){
        Login checkLogin = loginRepository.findWithUserId(login.getUserId()).orElseThrow(() -> new UserException("아이디 또는 비밀번호를 잘못 입력하셨습니다."));

        if(!checkLogin.getPassword().equals(login.getPassword()))
            throw new UserException("아이디 또는 비밀번호를 잘못 입력하셨습니다.");

        return checkLogin;
    }

    public void withdrawal(Long id){
        Login login = loginRepository.findById(id);
        if(login.getIdentity().equals(Identity.STUDENT))
            loginRepository.remove(id);
        else if(login.getIdentity().equals(Identity.TEACHER))
            teacherRepository.remove(id);
    }

    public LoginEditDTO loginDetail(Long id){
        Login login = loginRepository.findById(id);
        if(login.getIdentity().equals(Identity.STUDENT))
        {
            return new LoginEditDTO(login);
        }
        else{
            Teacher teacher = teacherRepository.findByLoginId(id).orElseThrow(() -> new UserException("해당 유저가 없습니다."));
            log.debug("teacher={}", teacher);
            return new LoginEditDTO(login, teacher);
        }
    }

    public void duplicateUserId(String userId, Long id){
        loginRepository.findWithUserId(userId).ifPresent((Login checkLogin)->{
            if(id != checkLogin.getId()){
                throw new UserException("중복된 아이디가 있습니다.");
            }
        });
    }


    public void edit(Long id, LoginEditDTO loginEditDTO){
        Login login = loginRepository.findById(id);
        login.edit(loginEditDTO);
    }
}
