package com.example.ploc.service;

import com.example.ploc.domain.Match;
import com.example.ploc.dto.MatchTableBoardListDTO;
import com.example.ploc.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchService {
    private final MatchRepository matchRepository;

    public Match create(Match match){
        return matchRepository.save(match);
    }

    public List<MatchTableBoardListDTO> matchList(Long id){
        return matchRepository.findALlWithName(id);
    }


    public List<MatchTableBoardListDTO> matchListWithType(Long id, String type, String search){
        return matchRepository.findALlWithNameType(id, type, search);
    }
}
