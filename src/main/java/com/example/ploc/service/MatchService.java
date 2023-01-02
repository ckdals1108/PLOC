package com.example.ploc.service;

import com.example.ploc.domain.Login;
import com.example.ploc.domain.Match;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.MatchTableBoardListDTO;
import com.example.ploc.dto.api.MatchSaveAPIForm;
import com.example.ploc.repository.LoginRepository;
import com.example.ploc.repository.MatchRepository;
import com.example.ploc.repository.TeacherRepository;
import com.example.ploc.service.web.FormDTO.MatchingSaveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchService {
    private final MatchRepository matchRepository;
    private final LoginRepository loginRepository;
    private final TeacherRepository teacherRepository;

    public Match create(Match match){
        return matchRepository.save(match);
    }

    public Match create(MatchSaveAPIForm matchSaveAPIForm){
        Login student = loginRepository.findById(matchSaveAPIForm.getStudentId());
        Teacher teacher = teacherRepository.findById(matchSaveAPIForm.getTeacherId());
        Match match = new Match(student, teacher, matchSaveAPIForm.getWageOfDay(), matchSaveAPIForm.getTimesOfWeek());
        return matchRepository.save(match);
    }

    public List<MatchTableBoardListDTO> matchList(Long id){
        return matchRepository.findALlWithName(id);
    }


    public List<MatchTableBoardListDTO> matchListWithType(Long id, String type, String search){
        return matchRepository.findALlWithNameType(id, type, search);
    }

    public void delete(Long id){
        matchRepository.remove(id);
    }
}
