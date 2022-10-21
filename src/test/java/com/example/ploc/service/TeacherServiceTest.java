package com.example.ploc.service;

import com.example.ploc.domain.Student;
import com.example.ploc.domain.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TeacherServiceTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    TeacherService teacherService;

    @Test
    @Rollback(false)
    public void 학생회원생성() throws Exception{
        Teacher teacher = new Teacher("min", "1234", "chang","suwon","java");
        teacherService.create(teacher);
    }
}