package com.example.ploc.dto;

import com.example.ploc.domain.Login;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchFormDTO {
    private String name;
    private String subject;
    private String university;
    private int wageOfDay;
    private int timesOfWeek;

    protected MatchFormDTO(){

    }

    public MatchFormDTO(String name, String subject, String university, int wageOfDay, int timesOfWeek) {
        this.name = name;
        this.subject = subject;
        this.university = university;
        this.wageOfDay = wageOfDay;
        this.timesOfWeek = timesOfWeek;
    }
}
