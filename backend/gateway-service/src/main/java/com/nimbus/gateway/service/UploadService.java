package com.nimbus.gateway.service;

import com.nimbus.gateway.client.MetadataClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UploadService {
    private final MetadataClient metadataClient;

    public String uploadFile(MultipartFile file){
        System.out.println("========== FILE RECEIVED ==========");
        System.out.println("File Name    : " + file.getOriginalFilename());
        System.out.println("File Size    : " + file.getSize());
        System.out.println("Content Type : " + file.getContentType());

        String objectId = metadataClient.createObject(
                file.getOriginalFilename(),
                file.getSize(),
                file.getContentType()
        );

        System.out.println("Generated Object ID : " + objectId);

        return objectId;
    }
}
