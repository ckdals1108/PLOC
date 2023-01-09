package com.example.ploc.controller.api;

import com.example.ploc.domain.Login;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.api.LoginAPIDTO;
import com.example.ploc.dto.api.ReturnList;
import com.example.ploc.dto.api.TeacherAPIDTO;
import com.example.ploc.dto.api.TeachersAPIDTO;
import com.example.ploc.dto.login.LoginDTO;
import com.example.ploc.service.LoginService;
import com.example.ploc.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api")
public class TeacherApiController {
    private final TeacherService teacherService;

    @GetMapping("/teachers")
    public ResponseEntity<List<TeachersAPIDTO>> teacherListAPI(){
        List<TeachersAPIDTO> teachers = teacherService.findAllWithLoginAPI();
        return ResponseEntity.status(HttpStatus.OK).body(teachers);
    }

    @GetMapping("/teacher/{id}")
    public ResponseEntity<TeacherAPIDTO> teacherDetail(@PathVariable Long id){
        TeacherAPIDTO teacherAPIDTO = teacherService.detailAPI(id);
        return ResponseEntity.status(HttpStatus.OK).body(teacherAPIDTO);
    }
}
