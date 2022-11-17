package com.example.ploc.service;

import com.example.ploc.domain.Student;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.LoginDTO;
import com.example.ploc.repository.StudentRepository;
import com.example.ploc.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public List<Teacher> chooseTeacher(Teacher teacher){
        return teacherRepository.findBySubject(teacher.getSubject());
    }

    public List<Teacher> allTeacher(){
        return teacherRepository.findByAll();
    }

    public Teacher join(LoginDTO teacher){
        Optional<Teacher> chkTeacher = teacherRepository.findByLoginId(teacher.getLoginId());
        if(chkTeacher.isPresent()){
            String originalPassword = chkTeacher.get().getPassword();
            if(teacher.getPassword().equals(originalPassword))
            {
                return chkTeacher.get();
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
