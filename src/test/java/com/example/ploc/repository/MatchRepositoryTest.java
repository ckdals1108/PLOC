package com.example.ploc.repository;

import com.example.ploc.domain.Match;
import com.example.ploc.domain.Student;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.MatchTableFormDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class MatchRepositoryTest {
    @Autowired private StudentRepository studentRepository;
    @Autowired private TeacherRepository teacherRepository;
    @Autowired private MatchRepository matchRepository;


    @Test
    public void findByStudent() throws Exception{
        //given
        Student student = new Student("qwer","qwer","qwer","qwer");
        Student student1 = new Student("qwer","qwer","qwer","qwer");
        studentRepository.save(student);
        studentRepository.save(student1);
        Teacher teacher = new Teacher("asdf","asdf","asdf","asdf","asdf");
        teacherRepository.save(teacher);
        Match match = new Match(student, teacher, 10000, 3);
        Match match1 = new Match(student1, teacher, 10000, 3);
        matchRepository.save(match);
        matchRepository.save(match1);

        //when
        List<MatchTableFormDTO> matchList = matchRepository.findByStudent(student);

        //then
        for(MatchTableFormDTO matchEach : matchList)
        {
            log.debug(matchEach.toString());
        }

    }

}