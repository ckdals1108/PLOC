package com.example.ploc.controller.api;

import com.example.ploc.dto.MatchTableBoardListDTO;
import com.example.ploc.dto.api.MatchSaveAPIForm;
import com.example.ploc.service.MatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/match")
public class MatchAPIController {
    private final MatchService matchService;

    @PostMapping("/create")
    public String matchingCreate(@RequestBody MatchSaveAPIForm matchSaveAPIForm){
        matchService.create(matchSaveAPIForm);
        return "success";
    }

    @GetMapping("/{id}")
    public ResponseEntity<List> matchingTable(@PathVariable Long id){
        List<MatchTableBoardListDTO> database = matchService.matchList(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(database);
    }

    @DeleteMapping("/delete/{id}")
    public String matchingDelete(@PathVariable Long id){
        matchService.delete(id);
        return "success";
    }
}
