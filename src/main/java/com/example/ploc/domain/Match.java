package com.example.ploc.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name="matching")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="matching_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="student_id")
    private Student student;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    //주급
    private int wageOfWeek;
    //주 몇일
    private int dayOfWeek;

    protected Match(){}

    public Match(Student student, Teacher teacher, int wageOfWeek, int dayOfWeek) {
        this.student = student;
        this.teacher = teacher;
        this.wageOfWeek = wageOfWeek;
        this.dayOfWeek = dayOfWeek;
    }
}
