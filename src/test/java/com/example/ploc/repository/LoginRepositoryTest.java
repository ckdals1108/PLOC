package com.example.ploc.repository;

import com.example.ploc.domain.Identity;
import com.example.ploc.domain.Login;
import com.example.ploc.domain.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class LoginRepositoryTest {
    @Autowired LoginRepository loginRepository;
    @Autowired TeacherRepository teacherRepository;

    @Test
    @Rollback(false)
    public void save() throws Exception{
        //given
        Login login = new Login("qwer", "qwer","qwer", Identity.STUDENT);

        //when
        loginRepository.save(login);

        //then
    }

    @Test
    @Rollback(false)
    public void join() throws Exception{
        //given
        Login login = new Login("qwer", "qwer","qwer", Identity.TEACHER);
        loginRepository.save(login);
        //when
//        Login login2 = loginRepository.findByUserId(login.getUserId());

        //then
//        assertThat(login).isEqualTo(login2);
    }

    @Test
    @Rollback(false)
    public void findTeacherId() throws Exception{
        //given
        Login login = new Login("qwer", "qwer","qwer", Identity.TEACHER);
        Teacher teacher = new Teacher("asdf", "asdf", login);
        teacherRepository.save(teacher);

        //when
        Login login1 = loginRepository.findByTeacherId(teacher.getId());

        //then
        log.debug(login1.toString());

    }
}