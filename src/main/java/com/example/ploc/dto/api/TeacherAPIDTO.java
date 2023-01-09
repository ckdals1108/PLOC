package com.example.ploc.dto.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherAPIDTO {
    private Long id;
    private String name;
    private String subject;
    private String university;
    private String upLoadFileName;
    private String storeFileName;
    private String filePath;
}
