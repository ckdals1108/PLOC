package com.example.ploc.dto;

import com.example.ploc.domain.Login;
import com.example.ploc.domain.Teacher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchTableBoardDTO {
    private String name;
    private Teacher teacher;

    protected MatchTableBoardDTO(){

    }

    public MatchTableBoardDTO(String name, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
    }
}
