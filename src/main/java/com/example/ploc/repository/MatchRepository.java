package com.example.ploc.repository;

import com.example.ploc.domain.Match;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class MatchRepository {
    @PersistenceContext
    EntityManager em;

    public Match save(Match match) {
        em.persist(match);
        return match;
    }
}
