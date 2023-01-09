package com.example.ploc.domain;

import lombok.Getter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Table(name="matching")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="matching_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="login_id")
    private Login login;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    //주 몇일
    @NotNull
    private int timesOfWeek;
    //주급
    @NotNull
    @NumberFormat(pattern="###,###")
    private int wageOfDay;


    protected Match(){}

    public Match(Login login, Teacher teacher, int wageOfDay, int timesOfWeek) {
        this.login = login;
        this.teacher = teacher;
        this.wageOfDay = wageOfDay;
        this.timesOfWeek = timesOfWeek;
    }

    public void removeLoginTeacher(){
        teacher = null;
        login = null;
    }
}
