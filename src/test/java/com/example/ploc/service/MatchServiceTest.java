package com.example.ploc.service;

import com.example.ploc.domain.Match;
import com.example.ploc.domain.Student;
import com.example.ploc.domain.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
class MatchServiceTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    MatchService matchService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;

    /*@Test @Rollback(false)
    public void 매칭(){
        Student student1 = new Student("woo", "1234", "chang","min");
        studentService.create(student1);
        Teacher teacher1 = new Teacher("min", "1234", "chang","suwon","java");
        teacherService.create(teacher1);
        Student student = em.find(Student.class,1L);
        Teacher teacher = em.find(Teacher.class, 1L);
        matchService.match(student, teacher, 50000, 3);
    }*/
}