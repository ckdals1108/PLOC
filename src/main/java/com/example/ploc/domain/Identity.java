package com.example.ploc.domain;

public enum Identity {
    STUDENT("학생"), TEACHER("대학생");

    private final String description;

    Identity(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
