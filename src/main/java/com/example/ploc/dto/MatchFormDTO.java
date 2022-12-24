package com.example.ploc.dto;

import com.example.ploc.domain.Teacher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchFormDTO {
    private String name;
    private String subject;
    private String university;
    private Integer wageOfDay;
    private Integer timesOfWeek;

    public MatchFormDTO(){

    }

    public MatchFormDTO(String name, String subject, String university) {
        this.name = name;
        this.subject = subject;
        this.university = university;
    }

    public MatchFormDTO(String name, String subject, String university, Integer wageOfDay, Integer timesOfWeek) {
        this.name = name;
        this.subject = subject;
        this.university = university;
        this.wageOfDay = wageOfDay;
        this.timesOfWeek = timesOfWeek;
    }
}
