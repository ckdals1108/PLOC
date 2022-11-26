package com.example.ploc.repository;

import com.example.ploc.domain.Match;
import com.example.ploc.dto.MatchTableBoardListDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Slf4j
public class MatchRepository {
    @PersistenceContext
    EntityManager em;

    public Match save(Match match){
        em.persist(match);
        return match;
    }

    public Match findById(Long matchId){
        return em.find(Match.class, matchId);
    }

    public List<Match> findALl(Long id) {
        return em.createQuery("select m from Match m where m.login.id = :id" +
                " order by m.login.id asc").setParameter("id", id).getResultList();
    }

    public List<MatchTableBoardListDTO> findALlWithName(Long id) {
        return em.createQuery("select new com.example.ploc.dto.MatchTableBoardListDTO(m.id, l.name, t.university, t.subject, m.timesOfWeek, m.wageOfDay) from Match m join m.teacher t join m.login l where m.login.id = :id" +
                " order by m.id asc").setParameter("id", id).getResultList();
    }

    public List<MatchTableBoardListDTO> findALlWithNameType(Long id, String type, String search) {
        if(type.equals("university"))
            return em.createQuery("select new com.example.ploc.dto.MatchTableBoardListDTO(m.id, l.name, t.university, t.subject, m.timesOfWeek, m.wageOfDay) from Match m join m.teacher t join m.login l where m.login.id = :id and t.university=:search" +
                    " order by m.id asc")
                    .setParameter("id", id)
                    .setParameter("search", search)
                    .getResultList();
        else
            return em.createQuery("select new com.example.ploc.dto.MatchTableBoardListDTO(m.id, l.name, t.university, t.subject, m.timesOfWeek, m.wageOfDay) from Match m join m.teacher t join m.login l where m.login.id = :id and t.subject=:search" +
                    " order by m.id asc")
                    .setParameter("id", id)
                    .setParameter("search", search)
                    .getResultList();
    }

}
