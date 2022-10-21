package com.example.ploc.repository;

import com.example.ploc.domain.Student;
import com.example.ploc.domain.Teacher;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class StudentRepository {
    @PersistenceContext
    EntityManager em;

    public Student save(Student student){
        em.persist(student);
        return student;
    }

    public Optional<Student> findById(Long id){
        Student student = em.find(Student.class, id);
        return Optional.ofNullable(student);
    }

    public Optional<Student> findByLoginId(String loginId){
        return em.createQuery("select s from Student s where s.loginId = :loginId", Student.class)
                                    .setParameter("loginId", loginId)
                                    .getResultList().stream().findAny();
    }

    public List<Student> findBySubject(String subject){
        return em.createQuery("select t from Teacher t where t.subject = :subject", Student.class)
                .setParameter("subject", subject)
                .getResultList();
    }
}
