package com.example.ploc.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", student=" + student +
                ", teacher=" + teacher +
                ", wageOfWeek=" + wageOfWeek +
                ", dayOfWeek=" + dayOfWeek +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return wageOfWeek == match.wageOfWeek && dayOfWeek == match.dayOfWeek && Objects.equals(id, match.id) && Objects.equals(student, match.student) && Objects.equals(teacher, match.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, teacher, wageOfWeek, dayOfWeek);
    }
}
