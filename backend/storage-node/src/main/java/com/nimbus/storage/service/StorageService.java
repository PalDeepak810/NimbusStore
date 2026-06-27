package com.nimbus.storage.service;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {
    private static final String STORAGE_DIR = "storage";


    public void store(String objectId, MultipartFile file)throws IOException {
        Path storagePath = Paths.get(STORAGE_DIR);


        if(!Files.exists(storagePath)){
            Files.createDirectories(storagePath);
        }

        String originalFilename = file.getOriginalFilename();

        String extension = "";


        if(originalFilename!=null&&originalFilename.contains(".")){
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        Path destination = storagePath.resolve(objectId+extension);

        Files.copy(file.getInputStream(),destination);

        System.out.println("Stored :"+ destination);
    }

    public UrlResource load(String objectId) throws IOException {

        Path storagePath = Paths.get(STORAGE_DIR);

        Path file = Files.list(storagePath)
                .filter(path -> path.getFileName().toString().startsWith(objectId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("File not found"));

        return new UrlResource(file.toUri());
    }
}
