package com.example.ploc.service;

import com.example.ploc.domain.Student;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.LoginDTO;
import com.example.ploc.repository.StudentRepository;
import com.example.ploc.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public List<Teacher> chooseTeacher(Student student){
        return teacherRepository.findBySubject(student.getSubject());
    }

    public Student join(LoginDTO student){
        Optional<Student> chkStudent = studentRepository.findByLoginId(student.getLoginId());
        if(chkStudent.isPresent()){
            String originalPassword = chkStudent.get().getPassword();
            if(student.getPassword().equals(originalPassword))
            {
                return chkStudent.get();
            }
            else{
                log.info("비밀번호가 틀립니다.");
                return null;
            }
        }else {
            log.info("해당하는 아이디가 없습니다.");
            return null;
        }
    }

    public void create(Student student){
        try{
            duplicateStudent(student);
            studentRepository.save(student);
        }catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    private void duplicateStudent(Student student){
        studentRepository.findByLoginId(student.getLoginId())
                .ifPresent(s -> {throw new IllegalStateException("중복된 회원이 있습니다.");});
    }
}