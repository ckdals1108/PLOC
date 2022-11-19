package com.example.ploc.dto;

import com.example.ploc.domain.Match;
import com.example.ploc.domain.Teacher;
import lombok.Data;

@Data
public class MatchTableFormDTO {
    private Long id;
    private String name;
    private String universityName;
    private String subject;
    //주급
    private int wageOfWeek;
    //주 몇일
    private int dayOfWeek;

    public MatchTableFormDTO(Match match, Teacher teacher) {
        this.id = match.getId();
        this.name = teacher.getName();
        this.universityName = teacher.getUniversityName();
        this.subject = teacher.getSubject();
        this.wageOfWeek = match.getWageOfWeek();
        this.dayOfWeek = match.getDayOfWeek();
    }

    public MatchTableFormDTO(Long id, String name, String getUniversityName, String subject, int wageOfWeek, int dayOfWeek) {
        this.id = id;
        this.name = name;
        this.universityName = getUniversityName;
        this.subject = subject;
        this.wageOfWeek = wageOfWeek;
        this.dayOfWeek = dayOfWeek;
    }
}
