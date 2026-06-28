package com.nimbus.gateway.service;

import com.nimbus.gateway.client.MetadataClient;
import com.nimbus.gateway.client.StorageClient;
import com.nimbus.gateway.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DownloadOrchestrator {
    private final MetadataClient metadataClient;
    private final StorageClient storageClient;

    public byte[] downloadObject(String objectId)throws IOException {
        ObjectMetadata metadata = metadataClient.getObject(objectId);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        for(int i=0;i<metadata.getChunkCount();i++){
            byte[] chunk= storageClient.getChunk(objectId,i);

            outputStream.write(chunk);
        }
        return outputStream.toByteArray();
    }
}
