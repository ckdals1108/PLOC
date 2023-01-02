package com.example.ploc.service.web.FormDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MatchingSaveDTO {
    private String name;
    private String subject;
    private String university;
    @NotNull
    private Integer wageOfDay;
    @NotNull
    private Integer timesOfWeek;

    public MatchingSaveDTO() {
    }

    public MatchingSaveDTO(Integer wageOfDay, Integer timesOfWeek) {
        this.wageOfDay = wageOfDay;
        this.timesOfWeek = timesOfWeek;
    }

    public MatchingSaveDTO(String name, String subject, String university, Integer wageOfDay, Integer timesOfWeek) {
        this.name = name;
        this.subject = subject;
        this.university = university;
        this.wageOfDay = wageOfDay;
        this.timesOfWeek = timesOfWeek;
    }
}
