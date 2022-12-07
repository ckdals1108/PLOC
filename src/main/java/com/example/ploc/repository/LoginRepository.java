package com.example.ploc.repository;

import com.example.ploc.domain.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class LoginRepository {
    @PersistenceContext
    EntityManager em;

    public Login save(Login login){
        em.persist(login);
        return login;
    }

    public Login findById(Long id){
        Login login = em.find(Login.class, id);
        return login;
    }

    public Login findByUserId(String userId){
        Object login = em.createQuery("select l from Login l where l.userId = :userId")
                .setParameter("userId", userId)
                .getSingleResult();
        return (Login)login;
    }

    public Login findByTeacherId(Long id){
        Object login = em.createQuery("select l from Login l join Teacher t where l.teacher.id=:teacherId")
                .setParameter("teacherId", id)
                .getSingleResult();
        return (Login)login;
    }

    public Login findTeacher(Long id){
        Object login = em.createQuery("select l from Login l join fetch l.teacher t" +
                " where l.teacher.id = :teacherId")
                .setParameter("teacherId", id)
                .getSingleResult();
        return (Login)login;
    }

    public void remove(Long id){
        Login login = em.find(Login.class, id);
        em.remove(login);
    }
}
