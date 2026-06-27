package com.nimbus.gateway.service;

import com.nimbus.gateway.client.MetadataClient;
import com.nimbus.gateway.client.StorageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UploadService {
    private final MetadataClient metadataClient;
    private final StorageClient storageClient;

    public String uploadFile(MultipartFile file)throws IOException {
        //Create metadata
        String objectId = metadataClient.createObject(
                file.getOriginalFilename(),
                file.getSize(),
                file.getContentType()
        );

        //Store file
        storageClient.storeObject(objectId,file);

        return objectId;
    }
}
