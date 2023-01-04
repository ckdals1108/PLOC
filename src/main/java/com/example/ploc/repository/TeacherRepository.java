package com.example.ploc.repository;

import com.example.ploc.domain.Teacher;
import org.springframework.stereotype.Repository;

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

    public Teacher findWithIdPhotoFile(Long id){
        List resultList = em.createQuery("select t from Teacher t join fetch t.idPhotoFile where t.login.id=:id")
                .setParameter("id",id)
                .getResultList();
        return (Teacher)resultList.get(0);
    }

    public void remove(Long id){
        Teacher teacher = findWithId(id);
        em.remove(teacher);
    }
}
