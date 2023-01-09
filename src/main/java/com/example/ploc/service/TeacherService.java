package com.example.ploc.service;

import com.example.ploc.domain.IdPhotoFile;
import com.example.ploc.domain.Login;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.MatchTableBoardDTO;
import com.example.ploc.dto.api.LoginAPIFormDTO;
import com.example.ploc.dto.api.TeacherAPIDTO;
import com.example.ploc.dto.api.TeachersAPIDTO;
import com.example.ploc.dto.file.UploadFile;
import com.example.ploc.dto.login.LoginEditDTO;
import com.example.ploc.dto.login.LoginFormDTO;
import com.example.ploc.exception.DatabaseResultError;
import com.example.ploc.exception.UserException;
import com.example.ploc.repository.IdPhotoFileRepository;
import com.example.ploc.repository.LoginRepository;
import com.example.ploc.repository.TeacherRepository;
import com.example.ploc.service.file.IdPhotoFileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final LoginRepository loginRepository;
    private final IdPhotoFileStore idPhotoFileStore;
    private final IdPhotoFileRepository idPhotoFileRepository;

    public Teacher create(LoginFormDTO user) throws IOException {
        Teacher teacher;
        Login login = new Login(user.getUserId(), user.getPassword(), user.getName(), user.getIdentity());
        loginRepository.findWithUserId(user.getUserId()).ifPresent((checkLogin)->{throw new UserException("중복된 회원ID가 있습니다");});
        UploadFile uploadFile = idPhotoFileStore.storeFile(user.getAttachFile());
        if(uploadFile != null){
            IdPhotoFile idPhotoFile = new IdPhotoFile(uploadFile.getUpLoadFileName(), uploadFile.getStoreFileName(), uploadFile.getFilePath());
            teacher = new Teacher(user.getSubject(), user.getUniversity(), login, idPhotoFile);
        }else{
            teacher = new Teacher(user.getSubject(), user.getUniversity(), login);
        }

        return teacherRepository.save(teacher);
    }

    public Teacher findByIdWithLogin(Long id){
        return teacherRepository.findByIdWithLogin(id).orElseThrow(() -> new DatabaseResultError("해당 데이터가 없습니다."));
    }

    public MatchTableBoardDTO findLogin(Long id){
        Teacher teacher = teacherRepository.findById(id);
        String name = teacher.getLogin().getName();
        return new MatchTableBoardDTO(name, teacher);
    }

    public List<Teacher> findAllWithLogin(){
        List<Teacher> teachers = teacherRepository.findAllWithLogin();
        if(!teachers.isEmpty())
            return teachers;
        else
            throw new DatabaseResultError("대학생 결과가 없습니다.");
    }

    public List<TeachersAPIDTO> findAllWithLoginAPI(){
        List<TeachersAPIDTO> teachers = teacherRepository.findAllWithLoginAPI();
        if(!teachers.isEmpty())
            return teachers;
        else
            throw new DatabaseResultError("대학생 결과가 없습니다.");
    }

    public TeacherAPIDTO detailAPI(Long id){
        Teacher teacher = teacherRepository.findByIdWithLogin(id).orElseThrow(() -> new DatabaseResultError("해당 데이터가 없습니다."));
        IdPhotoFile idPhotoFile = teacher.getIdPhotoFile();
        return new TeacherAPIDTO(teacher.getId(), teacher.getLogin().getName(), teacher.getSubject(), teacher.getUniversity(), idPhotoFile.getUpLoadFileName(), idPhotoFile.getStoreFileName(), idPhotoFile.getFilePath());
    }

    public void edit(Long id, LoginEditDTO loginEditDTO, MultipartFile changeFile, Boolean remove) throws IOException {
        Teacher teacher = teacherRepository.findByLoginId(id).orElseThrow(() -> new UserException("해당 유저가 없습니다."));

        UploadFile upLoadFile = null;

        if(remove == true)
        {
            try {
                idPhotoFileRepository.remove(teacher.getIdPhotoFile().getId());
                teacher.removeIdPhotoFile();
            }catch(Exception e){
                log.debug("해당파일이 없습니다 , {}",e.getMessage());
            }
        }

        if(!changeFile.isEmpty()) {
            upLoadFile = idPhotoFileStore.storeFile(changeFile);
            teacher.edit(loginEditDTO,upLoadFile);
        }
        else{
            teacher.edit(loginEditDTO);
        }
    }

    public void remove(Long id){
        teacherRepository.remove(id);
    }
}
