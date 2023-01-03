package com.example.ploc.service.file;

import com.example.ploc.domain.IdPhotoFile;
import com.example.ploc.dto.file.UploadFile;
import com.example.ploc.repository.IdPhotoFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class IdPhotoFileStore {
    private final IdPhotoFileRepository idPhotoFileRepository;

    @Value("${file.dir}")
    private String fileDir;
    private String idPhoto = "/idPhoto";

    public String getFullPath(String fileName) {
        return fileDir + datePath() + fileName;
    }

    public String getFileUrlPath(String fileName, String filePath){
        return filePath + fileName;
    }

    private String getFolderPath(){
        return fileDir + datePath();
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            return null;
        }

        String originalFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFileName);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new UploadFile(originalFileName, storeFileName, getFolderPath());
    }

    private String datePath() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String path = now.format(formatter);
        File folder = new File(fileDir + path);

        if(!folder.exists())
            folder.mkdir();


        folder = new File(fileDir + path + idPhoto);

        if(!folder.exists())
            folder.mkdir();

        return path + idPhoto + "/";
    }

    private String createStoreFileName(String originalFileName){
        String ext = extractExt(originalFileName);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFileName){
        int pos = originalFileName.indexOf(".");
        return originalFileName.substring(pos + 1);
    }
}
