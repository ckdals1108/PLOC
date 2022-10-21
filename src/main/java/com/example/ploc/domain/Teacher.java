package com.example.ploc.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Teacher extends BaseLogin{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="teacher_id")
    private Long id;

    private String name;
    private String universityName;
    private String subject;

    @OneToOne(fetch = FetchType.LAZY, mappedBy="teacher")
    private Match match;

    protected Teacher() {

    }

    public Teacher(String loginId, String password, String name, String universityName, String subject) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.universityName = universityName;
        this.subject = subject;
    }
}
