package com.nimbus.gateway.service;

import com.nimbus.gateway.client.MetadataClient;
import com.nimbus.gateway.client.StorageClient;
import com.nimbus.gateway.model.Chunk;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UploadOrchestrator {
    private final MetadataClient metadataClient;
    private final StorageClient storageClient;
    private final ChunkService chunkService;

    public String uploadFile(MultipartFile file) throws IOException {

        //Split into chunks
        List<Chunk> chunks = chunkService.splitIntoChunks(file);

        //Create metadata
        String objectId = metadataClient.createObject(
                file.getOriginalFilename(),
                file.getSize(),
                file.getContentType(),
                chunks.size()
        );


        //Upload each chunk
        for (Chunk chunk : chunks) {
            storageClient.storeChunk(objectId, chunk);
        }

        return objectId;
    }
}
