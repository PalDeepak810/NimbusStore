package com.nimbus.gateway.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

    public void uploadFile(MultipartFile file) {

        System.out.println("========== FILE RECEIVED ==========");
        System.out.println("File Name    : " + file.getOriginalFilename());
        System.out.println("File Size    : " + file.getSize() + " bytes");
        System.out.println("Content Type : " + file.getContentType());
        System.out.println("==================================");
    }
}
