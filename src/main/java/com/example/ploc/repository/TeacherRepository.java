package com.example.ploc.repository;

import com.example.ploc.domain.Student;
import com.example.ploc.domain.Teacher;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class TeacherRepository {
    @PersistenceContext
    EntityManager em;

    public Teacher save(Teacher teacher){
        em.persist(teacher);
        return teacher;
    }

    public List<Teacher> findByAll(){
        return em.createQuery("select t from Teacher t", Teacher.class).getResultList();
    }

    public Optional<Teacher> findById(Long id){
        Teacher teacher = em.find(Teacher.class, id);
        return Optional.ofNullable(teacher);
    }

    public Optional<Teacher> findByLoginId(String loginId){
        return em.createQuery("select t from Teacher t where t.loginId = :loginId", Teacher.class)
                .setParameter("loginId", loginId)
                .getResultList().stream().findAny();
    }

    public List<Teacher> findBySubject(String subject){
        return em.createQuery("select t from Teacher t where t.subject = :subject", Teacher.class)
                .setParameter("subject", subject)
                .getResultList();
    }
}
