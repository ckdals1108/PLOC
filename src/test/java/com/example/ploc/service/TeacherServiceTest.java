package com.example.ploc.service;

import com.example.ploc.domain.IdPhotoFile;
import com.example.ploc.domain.Identity;
import com.example.ploc.domain.Login;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.file.UploadFile;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class TeacherServiceTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private LoginService loginService;
    
    @Test
    @Rollback(false)
    public void 회원가입_증명서_더하기_버전() throws Exception{
        //given
        Login login = new Login("testT","testT","testT",Identity.TEACHER);
        IdPhotoFile idPhotoFile = new IdPhotoFile("asdf","asdf");
        Teacher teacher = new Teacher("min", "chang",login, idPhotoFile);
        //when
        Teacher teacher1 = teacherService.create(teacher);
        idPhotoFile.changeTeacher(teacher);
        //then
        log.debug("teacher={}",teacher1);
    }


    @Test
    @Rollback(false)
    public void findbyLogin() throws Exception{
        //given
        Login login = new Login("qwer","qwer","qwer", Identity.TEACHER);
        Teacher teacher = new Teacher("min", "1234",login);
        teacherService.create(teacher);

        //when
        //Login result = teacherService.findLogin(1L);

        //then
        //log.debug("teacher={}",result.toString());

    }

    @Test
    @Rollback(false)
    public void 회원삭제() throws Exception{
        teacherService.remove(1L);
    }

    /*@Test
    public void findById() throws Exception{
        //given
        Teacher teacher = new Teacher("min", "1234", "chang","suwon","java");
        teacherService.create(teacher);

        //when
        Teacher teacher1 = teacherService.findById(1L).get();

        //then
        assertThat(teacher).isEqualTo(teacher1);
        log.debug("teacher={}",teacher.getName());
    }
 */
}