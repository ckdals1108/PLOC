package com.example.ploc.repository;

import com.example.ploc.domain.IdPhotoFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class IdPhotoFileRepository {
    @PersistenceContext
    EntityManager em;

    public IdPhotoFile save(IdPhotoFile idPhotoFile){
        em.persist(idPhotoFile);
        return idPhotoFile;
    }

    public IdPhotoFile findById(Long id){
        IdPhotoFile idPhotoFile = em.find(IdPhotoFile.class, id);
        return idPhotoFile;
    }

    public void remove(Long id){
        IdPhotoFile idPhotoFile = em.find(IdPhotoFile.class, id);
        em.remove(idPhotoFile);
    }
}
