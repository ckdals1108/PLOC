package com.example.ploc.dto.teacher;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherListDTO {
    private Long id;
    private String name;
    private String university;
    private String subject;

    public TeacherListDTO(Long id, String name, String university, String subject) {
        this.id = id;
        this.name = name;
        this.university = university;
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "TeacherListDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", university='" + university + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
