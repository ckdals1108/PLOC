package com.example.ploc.domain;

import com.example.ploc.dto.file.UploadFile;
import com.example.ploc.dto.login.LoginEditDTO;
import com.example.ploc.dto.login.LoginFormDTO;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="teacher_id")
    private Long id;

    @NotBlank
    private String subject;
    @NotBlank
    private String university;

    @OneToMany(mappedBy="teacher", orphanRemoval = true)
    private List<Match> match;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="login_id")
    private Login login;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
    @JoinColumn(name="idPhotoFile_id")
    private IdPhotoFile idPhotoFile;

    protected Teacher() {

    }

    public Teacher(String subject, String university, Login login) {
        this.subject = subject;
        this.university = university;
        this.login = login;
    }

    public Teacher(String subject, String university, Login login, IdPhotoFile idPhotoFile) {
        this.subject = subject;
        this.university = university;
        this.login = login;
        this.idPhotoFile = idPhotoFile;
    }

    public void edit(LoginEditDTO loginEditDTO){
        subject = loginEditDTO.getSubject();
        university = loginEditDTO.getUniversity();
        login.edit(loginEditDTO);
    }

    public void edit(LoginEditDTO loginEditDTO, UploadFile uploadFile){
        subject = loginEditDTO.getSubject();
        university = loginEditDTO.getUniversity();
        login.edit(loginEditDTO);
        idPhotoFile = new IdPhotoFile(uploadFile.getUpLoadFileName(), uploadFile.getStoreFileName(), uploadFile.getFilePath());
    }

    public void changeIdPhotoFile(IdPhotoFile idPhotoFile){
        this.idPhotoFile = idPhotoFile;
    }

    public void removeIdPhotoFile(){
        idPhotoFile = null;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", university='" + university + '\'' +
                ", match=" + match +
                ", login=" + login +
                ", idPhotoFile=" + idPhotoFile +
                '}';
    }
}
