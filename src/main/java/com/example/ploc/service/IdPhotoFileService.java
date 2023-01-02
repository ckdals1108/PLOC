package com.example.ploc.service;

import com.example.ploc.repository.IdPhotoFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IdPhotoFileService {
    private final IdPhotoFileRepository idPhotoFileRepository;


}
