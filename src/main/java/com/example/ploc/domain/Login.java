package com.example.ploc.domain;

import com.example.ploc.dto.LoginFormDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="login_id")
    private Long id;

    private String userId;
    private String password;
    private String name;

    @OneToMany(mappedBy="login", cascade= CascadeType.REMOVE, orphanRemoval = true)
    private List<Match> match;

    @OneToOne(mappedBy="login", fetch = FetchType.LAZY, orphanRemoval = true)
    private Teacher teacher;

    @Enumerated(EnumType.STRING)
    private Identity identity;

    protected Login(){

    }

    public Login(String userId, String password, String name, Identity identity) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.identity = identity;
    }

    public void removeTeacher(){
        teacher = null;
    }

    public void edit(LoginFormDTO login){
        this.userId = login.getUserId();
        this.password = login.getPassword();
        this.name = login.getName();
        this.identity = login.getIdentity();
    }

    public void edit(Login login, Teacher teacher){
        this.userId = login.getUserId();
        this.password = login.getPassword();
        this.name = login.getName();
        this.identity = login.getIdentity();
        this.teacher = teacher;
    }


    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", identity=" + identity +
                '}';
    }
}
