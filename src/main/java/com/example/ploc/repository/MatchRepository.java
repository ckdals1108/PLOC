package com.example.ploc.repository;

import com.example.ploc.domain.Match;
import com.example.ploc.domain.Student;
import com.example.ploc.dto.MatchTableFormDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class MatchRepository {
    @PersistenceContext
    EntityManager em;

    public Match save(Match match) {
        em.persist(match);
        return match;
    }

    public List<Match> findAll(){
        return em.createQuery("select m from Match m", Match.class).getResultList();
    }

    public List<MatchTableFormDTO> findByStudent(Student student){
        return em.createQuery(
                "select new com.example.ploc.dto.MatchTableFormDTO(m.id, t.name, t.universityName, t.subject, m.wageOfWeek, m.dayOfWeek) from Match m join m.teacher t join m.student s " +
                        "where s.id = :studentId")
                .setParameter("studentId",student.getId())
                .getResultList();
    }

    public List<MatchTableFormDTO> findByStudentWithType(Student student, String type, String value){
        if(type.equals("universityName"))
            return em.createQuery(
                    "select new com.example.ploc.dto.MatchTableFormDTO(m.id, t.name, t.universityName, t.subject, m.wageOfWeek, m.dayOfWeek) from Match m join m.teacher t join m.student s " +
                            "where s.id = :studentId and t.universityName = :value")
                    .setParameter("studentId", student.getId())
                    .setParameter("value",value)
                    .getResultList();
        else if(type.equals("subject"))
            return em.createQuery(
                    "select new com.example.ploc.dto.MatchTableFormDTO(m.id, t.name, t.universityName, t.subject, m.wageOfWeek, m.dayOfWeek) from Match m join m.teacher t join m.student s " +
                            "where s.id = :studentId and t.subject = :value")
                    .setParameter("studentId", student.getId())
                    .setParameter("value",value)
                    .getResultList();
        return null;
    }
}
