package com.nimbus.metadata.service;

import com.nimbus.metadata.entity.ObjectMetadata;
import com.nimbus.metadata.entity.ObjectStatus;
import com.nimbus.metadata.repository.ObjectMetadataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ObjectService {
    private final ObjectMetadataRepository objectMetadataRepository;

    public UUID createObject(
            String filename,
            Long size,
            String contentType,
            Integer chunkCount
    ){
        ObjectMetadata object = new ObjectMetadata();
        UUID objectId = UUID.randomUUID();

        object.setId(objectId);
        object.setFilename(filename);
        object.setSize(size);
        object.setContentType(contentType);
        object.setStatus(ObjectStatus.RECEIVED);
        object.setCreatedAt(LocalDateTime.now());
        object.setChunkCount(chunkCount);

        objectMetadataRepository.save(object);
        return objectId;
    }

    public ObjectMetadata getObject(UUID objectId) {

        return objectMetadataRepository.findById(objectId)
                .orElseThrow(() -> new RuntimeException("Object not found"));
    }
}
