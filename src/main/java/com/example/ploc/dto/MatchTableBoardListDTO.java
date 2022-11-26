package com.example.ploc.dto;

import com.example.ploc.domain.Match;
import com.example.ploc.domain.Teacher;
import lombok.Getter;

@Getter
public class MatchTableBoardListDTO {
    private Long id;
    private String name;
    private String university;
    private String subject;
    private int timesOfWeek;
    //주급
    private int wageOfDay;

    protected MatchTableBoardListDTO(){

    }

    public MatchTableBoardListDTO(Long id, String name, String university, String subject,int timesOfWeek, int wageOfDay) {
        this.id = id;
        this.name = name;
        this.university = university;
        this.subject = subject;
        this.timesOfWeek = timesOfWeek;
        this.wageOfDay = wageOfDay;
    }

    @Override
    public String toString() {
        return "MatchTableBoardListDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", university='" + university + '\'' +
                ", subject='" + subject + '\'' +
                ", timesOfWeek=" + timesOfWeek +
                ", wageOfDay=" + wageOfDay +
                '}';
    }
}
