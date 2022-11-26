package com.example.ploc.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="teacher_id")
    private Long id;

    private String subject;
    private String university;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="login_login_id")
    private Login login;

    protected Teacher() {

    }

    public Teacher(String subject, String university, Login login) {
        this.subject = subject;
        this.university = university;
        this.login = login;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", university='" + university + '\'' +
                ", login=" + login +
                '}';
    }
}
