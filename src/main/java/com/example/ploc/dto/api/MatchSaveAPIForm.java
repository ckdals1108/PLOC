package com.example.ploc.dto.api;

import lombok.Data;

@Data
public class MatchSaveAPIForm {
    private Long studentId;
    private Long teacherId;
    private Integer wageOfDay;
    private Integer timesOfWeek;

    protected MatchSaveAPIForm(){

    }

    public MatchSaveAPIForm(Long studentId, Long teacherId, Integer wageOfDay, Integer timesOfWeek) {
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.wageOfDay = wageOfDay;
        this.timesOfWeek = timesOfWeek;
    }
}
