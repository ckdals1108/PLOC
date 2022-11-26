package com.example.ploc.service;

import com.example.ploc.domain.Login;
import com.example.ploc.dto.LoginDTO;
import com.example.ploc.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    private final LoginRepository loginRepository;

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


}
