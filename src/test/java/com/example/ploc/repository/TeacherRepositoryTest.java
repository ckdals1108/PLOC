package com.example.ploc.repository;

import com.example.ploc.domain.Identity;
import com.example.ploc.domain.Login;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.TeacherListDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class TeacherRepositoryTest {
    @Autowired private TeacherRepository teacherRepository;
    @Autowired private LoginRepository loginRepository;

    @Test
    @Rollback(false)
    public void save() throws Exception{
        //given
        Login login = new Login("qwer", "qwer","qwer", Identity.TEACHER);
        Teacher teacher = new Teacher("qwer","qwer", login);
        Login login1 = new Login("qwer", "qwer","qwer", Identity.STUDENT);

        //when
        loginRepository.save(login1);
        teacherRepository.save(teacher);

        //then
    }

    @Test
    @Rollback(false)
    public void findAll() throws Exception{
        //given
        Login login = new Login("qwer", "qwer","qwer", Identity.TEACHER);
        Teacher teacher1 = new Teacher("qwer","qwer", login);
        teacherRepository.save(teacher1);
        //when
        List<Teacher> teachers = teacherRepository.findAll();

        //then
        for(Teacher teacher : teachers){
            log.debug(teacher.toString());
        }
    }

    @Test
    @Rollback(false)
    public void findAllWithName() throws Exception{
        //given
        Login login = new Login("qwer", "qwer","qwer", Identity.TEACHER);
        Teacher teacher1 = new Teacher("qwer","qwer", login);
        teacherRepository.save(teacher1);

        //when
        List<Teacher> teachers = teacherRepository.findAllWithName();

        //then
        for(Teacher teacher : teachers)
        {
            log.debug("teacher={}",teacher.toString());
            log.debug("teacherName={}",teacher.getLogin().getName());
        }
    }

    @Test
    @Rollback(false)
    public void findById() throws Exception{
        //given
        Login login = new Login("qwer", "qwer","qwer", Identity.TEACHER);
        Teacher teacher1 = new Teacher("qwer","qwer", login);
        teacherRepository.save(teacher1);

        //when
        Teacher teacher = teacherRepository.findById(teacher1.getId());

        //then
        log.debug("teacher={}", teacher.toString());
        log.debug("login_name={}", teacher.getLogin().getName());
    }
}