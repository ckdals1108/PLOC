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
        return em.find(Login.class, id);
    }

    public Optional<Login> findWithUserId(String userId){
        List<Login> list = em.createQuery("select l from Login l where l.userId = :userId", Login.class)
                .setParameter("userId", userId)
                .getResultList();
        if(!list.isEmpty())
            return Optional.ofNullable(list.get(0));
        else
            return Optional.empty();
    }

    public void remove(Long id){
        Login login = em.find(Login.class, id);
        em.remove(login);
    }
}
