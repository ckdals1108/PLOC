package com.example.ploc.dto.file;

import lombok.Data;

@Data
public class UploadFile {
    private String upLoadFileName;
    private String storeFileName;
    private String filePath;

    public UploadFile(String upLoadFileName, String storeFileName) {
        this.upLoadFileName = upLoadFileName;
        this.storeFileName = storeFileName;
    }

    public UploadFile(String upLoadFileName, String storeFileName, String filePath) {
        this.upLoadFileName = upLoadFileName;
        this.storeFileName = storeFileName;
        this.filePath = filePath;
    }
}
