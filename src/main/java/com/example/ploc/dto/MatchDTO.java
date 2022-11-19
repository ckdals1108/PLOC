package com.example.ploc.dto;

import com.example.ploc.domain.Student;
import com.example.ploc.domain.Teacher;
import lombok.Data;

@Data
public class MatchDTO {
    private Long id;
    private Student student;
    private Teacher teacher;
    //주급
    private int wageOfWeek;
    //주 몇일
    private int dayOfWeek;

    /**
     *
     * @param student
     * @param teacher
     * @param wageOfWeek
     * @param dayOfWeek
     */
    public MatchDTO(Student student, Teacher teacher, int wageOfWeek, int dayOfWeek) {
        this.student = student;
        this.teacher = teacher;
        this.wageOfWeek = wageOfWeek;
        this.dayOfWeek = dayOfWeek;
    }
}
