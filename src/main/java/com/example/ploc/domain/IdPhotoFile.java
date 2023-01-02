package com.example.ploc.domain;

import lombok.Getter;

import javax.persistence.*;


@Entity
@Getter
public class IdPhotoFile {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idPhotoFile_id")
    private Long id;
    
    @OneToOne(mappedBy="idPhotoFile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Teacher teacher;

    private String upLoadFileName;
    private String storeFileName;

    public IdPhotoFile(String upLoadFileName, String storeFileName) {
        this.upLoadFileName = upLoadFileName;
        this.storeFileName = storeFileName;
    }

    public IdPhotoFile(Teacher teacher, String upLoadFileName, String storeFileName) {
        this.teacher = teacher;
        this.upLoadFileName = upLoadFileName;
        this.storeFileName = storeFileName;
    }

    public void changeTeacher(Teacher teacher){
        this.teacher = teacher;
    }
}
