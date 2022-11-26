package com.example.ploc.domain;

import lombok.Getter;

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

    @OneToMany(mappedBy="login",cascade= CascadeType.ALL, orphanRemoval = true)
    private List<Match> match;

    @OneToOne(mappedBy="login", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
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
