package com.example.ploc.service;

import com.example.ploc.domain.Student;
import com.example.ploc.domain.Teacher;
import com.example.ploc.repository.StudentRepository;
import com.example.ploc.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public List<Student> chooseStudent(Teacher teacher){
        return studentRepository.findBySubject(teacher.getSubject());
    }

    public Teacher join(Teacher teacher){
        Optional<Teacher> chkTeacher = teacherRepository.findByLoginId(teacher.getLoginId());
        if(chkTeacher.isPresent()){
            String originalPassword = chkTeacher.get().getPassword();
            if(teacher.getPassword().equals(originalPassword))
            {
                return chkTeacher.get();
            }
            else{
                throw new IllegalStateException("비밀번호가 틀립니다.");
            }
        }else {
            throw new IllegalStateException("해당하는 아이디가 없습니다.");
        }
    }

    public void create(Teacher teacher){
        try{
            duplicateTeacher(teacher);
            teacherRepository.save(teacher);
        }catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    private void duplicateTeacher(Teacher teacher){
        teacherRepository.findByLoginId(teacher.getLoginId())
                .ifPresent(s -> {throw new IllegalStateException("중복된 회원이 있습니다.");});
    }

}
