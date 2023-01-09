package com.example.ploc.domain;

import com.example.ploc.dto.login.LoginEditDTO;
import com.example.ploc.dto.login.LoginFormDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@EqualsAndHashCode
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="login_id")
    private Long id;

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @OneToMany(mappedBy="login", cascade= CascadeType.REMOVE, orphanRemoval = true)
    private List<Match> match;

    @OneToOne(mappedBy="login", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
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

    public void edit(LoginEditDTO login){
        this.userId = login.getUserId();
        this.password = login.getPassword();
        this.name = login.getName();
        this.identity = login.getIdentity();
    }
}
