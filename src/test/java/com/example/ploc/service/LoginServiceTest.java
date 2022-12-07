package com.example.ploc.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
@Transactional
class LoginServiceTest {
    @Autowired
    private LoginService loginService;

    @Test
    @Rollback(false)
    public void 회원삭제() throws Exception{
        //given

        //when
        loginService.withdrawal(2L);
        //then

    }
}