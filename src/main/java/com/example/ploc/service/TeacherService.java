package com.example.ploc.service;

import com.example.ploc.domain.IdPhotoFile;
import com.example.ploc.domain.Login;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.LoginFormDTO;
import com.example.ploc.dto.MatchTableBoardDTO;
import com.example.ploc.dto.TeacherDTO;
import com.example.ploc.dto.file.UploadFile;
import com.example.ploc.repository.LoginRepository;
import com.example.ploc.repository.TeacherRepository;
import com.example.ploc.service.file.IdPhotoFileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final LoginRepository loginRepository;
    private final IdPhotoFileStore idPhotoFileStore;

    public Teacher create(Teacher teacher){
        return teacherRepository.save(teacher);
    }

    public Teacher create(LoginFormDTO user) throws IOException {
        Login login = new Login(user.getUserId(), user.getPassword(), user.getName(), user.getIdentity());
        UploadFile uploadFile = idPhotoFileStore.storeFile(user.getAttachFile());
        IdPhotoFile idPhotoFile = new IdPhotoFile(uploadFile.getUpLoadFileName(), uploadFile.getStoreFileName());
        Teacher teacher = new Teacher(user.getSubject(), user.getUniversity(), login, idPhotoFile);
        return teacherRepository.save(teacher);
    }

    public Teacher findById(Long id){return teacherRepository.findById(id);}

    public Teacher findWithId(Long id){
        return teacherRepository.findWithId(id);
    }

    public Teacher findWithLoginId(Long id){
        Teacher teacher = teacherRepository.findWithLoginId(id);
        IdPhotoFile idPhotoFile = teacher.getIdPhotoFile();
        return teacher;
    }

    public Teacher findWithIdPhotoFile(Long id){
        Teacher teacher = teacherRepository.findWithIdPhotoFile(id);
        return teacher;
    }

    public TeacherDTO findTeacherWithLogin(Long id){
        Teacher teacher = teacherRepository.findWithId(id);
        return new TeacherDTO(teacher);
    }

    public List<Teacher> findAllWithName(){
        return teacherRepository.findAllWithName();
    }

    public MatchTableBoardDTO findLogin(Long id){
        Teacher teacher = teacherRepository.findById(id);
        String name = teacher.getLogin().getName();
        return new MatchTableBoardDTO(name, teacher);
    }

    public void remove(Long id){
        teacherRepository.remove(id);
    }
}
