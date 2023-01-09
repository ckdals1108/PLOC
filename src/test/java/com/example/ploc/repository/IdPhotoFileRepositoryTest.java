package com.example.ploc.repository;

import com.example.ploc.domain.IdPhotoFile;
import com.example.ploc.domain.Identity;
import com.example.ploc.domain.Login;
import com.example.ploc.domain.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class IdPhotoFileRepositoryTest {
    @Autowired private IdPhotoFileRepository idPhotoFileRepository;
    @Autowired private TeacherRepository teacherRepository;

    @Test
    @Rollback(false)
    public void 로그인Id검색테스트() throws Exception{
        //given
        Login login = new Login("테스트","테스트","테스트", Identity.STUDENT);
        IdPhotoFile idPhotoFile = new IdPhotoFile("asdf","asdf","asdf");
        Teacher teacher = new Teacher("컴퓨터","수원",login, idPhotoFile);

        Teacher save = teacherRepository.save(teacher);

        log.debug("teacher={}",save);

        //when


        //then
        idPhotoFileRepository.findByLoginId(teacher.getLogin().getId())
                .ifPresent((IdPhotoFile database)->log.debug("IdPhotoFile = {}", database));
    }

}