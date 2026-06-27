package com.nimbus.metadata.service;

import com.nimbus.metadata.entity.ObjectMetadata;
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
            String contentType
    ){
        ObjectMetadata object = new ObjectMetadata();
        UUID objectId = UUID.randomUUID();

        object.setId(objectId);
        object.setFilename(filename);
        object.setSize(size);
        object.setContentType(contentType);
        object.setStatus("Received");
        object.setCreatedAt(LocalDateTime.now());

        objectMetadataRepository.save(object);
        return objectId;
    }
}
