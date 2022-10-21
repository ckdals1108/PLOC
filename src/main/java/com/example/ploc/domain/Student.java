package com.example.ploc.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Student extends BaseLogin{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="student_id")
    private Long id;

    private String name;
    private String subject;

    @OneToOne(fetch = FetchType.LAZY, mappedBy="student")
    private Match match;

    protected Student(){

    }

    public Student(String loginId, String password, String name, String subject) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.subject = subject;
    }
}