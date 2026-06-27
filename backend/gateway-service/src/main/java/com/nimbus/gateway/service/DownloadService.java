package com.nimbus.gateway.service;

import com.nimbus.gateway.client.StorageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;
@Service
@RequiredArgsConstructor
public class DownloadService {
    private final StorageClient storageClient;

    public Resource downloadObject(String objectId){
        return storageClient.downloadObject(objectId);
    }
}
