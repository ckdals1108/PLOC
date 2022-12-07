package com.example.ploc.domain;

import com.example.ploc.dto.LoginFormDTO;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="teacher_id")
    private Long id;

    private String subject;
    private String university;

    @OneToMany(mappedBy="login", orphanRemoval = true)
    private List<Match> match;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="login_login_id")
    private Login login;

    protected Teacher() {

    }

    public Teacher(String subject, String university, Login login) {
        this.subject = subject;
        this.university = university;
        this.login = login;
    }

    public void setTeamToProxy(Login login){
        this.login = login;
    }

    public void edit(LoginFormDTO login){
        subject = login.getSubject();
        university = login.getUniversity();
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
