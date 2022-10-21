package com.example.ploc.service;

import com.example.ploc.domain.Match;
import com.example.ploc.domain.Student;
import com.example.ploc.domain.Teacher;
import com.example.ploc.repository.MatchRepository;
import com.example.ploc.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@RequiredArgsConstructor
public class MatchService {
    @PersistenceContext
    EntityManager em;

    private final MatchRepository matchesRepository;
    private final TeacherRepository teacherRepository;

    private void match(Student student, Teacher teacher, int wageOfWeek, int dayOfWeek) {
        Match match = new Match(student, teacher, wageOfWeek, dayOfWeek);
        em.persist(match);
    }
}
