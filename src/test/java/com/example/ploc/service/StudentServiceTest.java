package com.example.ploc.service;

import com.example.ploc.domain.Student;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
class StudentServiceTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    StudentService studentService;

    /*@Test @Rollback(false)
    public void 학생회원생성() throws Exception{
        Student student1 = new Student("woo", "1234", "chang","min");
        Student student2 = new Student("woo", "1234", "chang","min");
        studentService.create(student1);
        //중복테스트
        studentService.create(student2);
    }

    @Test @Rollback(false)
    public void 로그인() throws Exception{
        Student student1 = new Student("woo", "1234", "chang","min");
        Student student2 = new Student("woo", "123", "chang","min");
        try{
            studentService.create(student1);
            Student student = studentService.join(student2);
        }catch(Exception e){
            e.printStackTrace();
        }finally{

        }
    }*/
}