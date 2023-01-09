package com.example.ploc.repository;

import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.api.TeachersAPIDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

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

    public Optional<Teacher> findByIdWithLogin(Long id){
        List<Teacher> teacher = em.createQuery("select t from Teacher t join fetch t.login where t.id=:id", Teacher.class)
                .setParameter("id", id)
                .getResultList();
        if(!teacher.isEmpty()){
            return Optional.of(teacher.get(0));
        }else{
            return Optional.empty();
        }
    }

    public List<Teacher> findAllWithLogin(){
        return em.createQuery("select t from Teacher t join fetch t.login", Teacher.class)
                .getResultList();
    }

    public List<TeachersAPIDTO> findAllWithLoginAPI(){
        return em.createQuery("select new com.example.ploc.dto.api.TeachersAPIDTO(t.id, t.login.name, t.subject, t.university) from Teacher t join t.login order by t.id asc")
                .getResultList();
    }

    public Optional<Teacher> findByLoginId(Long id){
        List<Teacher> teacher = em.createQuery("select t from Teacher t where t.login.id=:id", Teacher.class)
                .setParameter("id", id)
                .getResultList();

        if(!teacher.isEmpty())
            return Optional.of(teacher.get(0));

        return  Optional.empty();
    }

    public Optional<Teacher> findByLoginIdWithLogin(Long id){
        List<Teacher> teacher = em.createQuery("select t from Teacher t join fetch t.login where t.login.id=:id", Teacher.class)
                .setParameter("id", id)
                .getResultList();

        if(!teacher.isEmpty())
            return Optional.of(teacher.get(0));

        return  Optional.empty();
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
        findByLoginId(id).ifPresent((Teacher checkTeacher)->em.remove(checkTeacher));
    }

    public void flush(){
        em.flush();
    }
}
