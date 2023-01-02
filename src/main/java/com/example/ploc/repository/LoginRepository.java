package com.example.ploc.repository;

import com.example.ploc.domain.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Slf4j
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

    public List findByUserId(String userId){
        List login = em.createQuery("select l from Login l where l.userId = :userId")
                .setParameter("userId", userId)
                .getResultList();
        return login;
    }

    public Login findByTeacherId(Long id){
        Object login = em.createQuery("select l from Login l join Teacher t where l.teacher.id=:teacherId")
                .setParameter("teacherId", id)
                .getResultList();
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
