package com.example.ploc.service;

import com.example.ploc.domain.Match;
import com.example.ploc.domain.Student;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.MatchTableFormDTO;
import com.example.ploc.repository.MatchRepository;
import com.example.ploc.repository.StudentRepository;
import com.example.ploc.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {
    @PersistenceContext
    EntityManager em;

    private final MatchRepository matchesRepository;

    public Match match(Student student, Teacher teacher, int wageOfWeek, int dayOfWeek) {
        Match match = new Match(student, teacher, wageOfWeek, dayOfWeek);
        matchesRepository.save(match);
        return match;
    }

    public List<Match> findAll(){
        return matchesRepository.findAll();
    }

    public List<MatchTableFormDTO> findByStudent(Student student){
        return matchesRepository.findByStudent(student);
    }

    public List<MatchTableFormDTO> findByStudentWithType(Student student, String type, String value){
        return matchesRepository.findByStudentWithType(student, type, value);
    }
}
