package com.example.ploc.service;

import com.example.ploc.domain.Login;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.MatchTableBoardDTO;
import com.example.ploc.repository.LoginRepository;
import com.example.ploc.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final LoginRepository loginRepository;

    public Teacher create(Teacher teacher){
        return teacherRepository.save(teacher);
    }

    public Teacher findWithId(Long id){
        return teacherRepository.findWithId(id);
    }

    public Teacher findById(Long id){return teacherRepository.findById(id);}

    public List<Teacher> findAllWithName(){
        return teacherRepository.findAllWithName();
    }

    public MatchTableBoardDTO findLogin(Long id){
        Teacher teacher = teacherRepository.findById(id);
        String name = teacher.getLogin().getName();
        return new MatchTableBoardDTO(name, teacher);
    }
}
