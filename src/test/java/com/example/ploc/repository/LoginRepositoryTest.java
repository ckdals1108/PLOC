package com.example.ploc.repository;

import com.example.ploc.domain.Identity;
import com.example.ploc.domain.Login;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Slf4j
class LoginRepositoryTest {
    @Autowired LoginRepository loginRepository;
    @Autowired TeacherRepository teacherRepository;

    @Test
    public void findWithUserId() throws Exception{
        //given
        Login temp = new Login("test","test","test", Identity.STUDENT);
        Login login = loginRepository.save(temp);

        //when
        Login test = (Login)loginRepository.findWithUserId("test").get(0);

        //then
        Assertions.assertThat(login).isEqualTo(test);
    }

}