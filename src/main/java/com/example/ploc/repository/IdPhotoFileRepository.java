package com.example.ploc.repository;

import com.example.ploc.domain.IdPhotoFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional
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

    public Optional<IdPhotoFile> findByTeacherId(Long id){
        List<IdPhotoFile> idPhotoFile = em.createQuery("select i from IdPhotoFile i where i.teacher.id=:id", IdPhotoFile.class)
                .setParameter("id", id)
                .getResultList();

        if(!idPhotoFile.isEmpty())
            return Optional.ofNullable(idPhotoFile.get(0));

        return Optional.empty();
    }

    public Optional<IdPhotoFile> findByLoginId(Long id){
        List<IdPhotoFile> idPhotoFile = em.createQuery("select i from IdPhotoFile i join i.teacher.login l where l.id =:id", IdPhotoFile.class)
                .setParameter("id", id)
                .getResultList();
        if(!idPhotoFile.isEmpty())
            return Optional.of(idPhotoFile.get(0));

        return Optional.empty();
    }

    public void remove(Long id){
        IdPhotoFile idPhotoFile = em.find(IdPhotoFile.class, id);
        File file = new File(idPhotoFile.getFilePath() + idPhotoFile.getStoreFileName());
        file.delete();
        em.remove(idPhotoFile);
    }
}
