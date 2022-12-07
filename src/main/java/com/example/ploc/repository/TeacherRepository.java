package com.example.ploc.repository;

import com.example.ploc.domain.Login;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.TeacherListDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TeacherRepository {
    @PersistenceContext
    EntityManager em;

    public Teacher save(Teacher teacher){
        em.persist(teacher);
        return teacher;
    }

    public Teacher findById(Long teacherId){
        Teacher teacher = em.find(Teacher.class, teacherId);
        return teacher;
    }

    public List<Teacher> findAll(){
        return em.createQuery("select t from Teacher t", Teacher.class)
                .getResultList();
    }

    public Teacher findWithId(Long id){
        Object teacher = em.createQuery("select t from Teacher t join fetch t.login" +
                " where t.id=:id")
                .setParameter("id", id)
                .getSingleResult();
        return (Teacher)teacher;
    }

    public Teacher findWithLoginId(Long id){
        Object teacher = em.createQuery("select t from Teacher t join fetch t.login l" +
                " where l.id=:id")
                .setParameter("id", id)
                .getSingleResult();
        return (Teacher)teacher;
    }

    public List<Teacher> findAllWithName(){
        return em.createQuery("select t from Teacher t join fetch t.login")
                .getResultList();
    }

    public void remove(Long id){
        Teacher teacher = findWithId(id);
        em.remove(teacher);
    }
}
