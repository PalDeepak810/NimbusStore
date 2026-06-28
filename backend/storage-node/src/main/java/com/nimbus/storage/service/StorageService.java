package com.nimbus.storage.service;


import com.nimbus.storage.constants.StorageConstants;
import com.nimbus.storage.model.Chunk;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {
    private static final String OBJECTS_DIR = StorageConstants.OBJECTS_DIRECTORY;

    private static final Logger log =
            LoggerFactory.getLogger(StorageService.class);



    public void storeChunk(String objectId, Chunk chunk) throws IOException {

        Path objectDirectory = Paths.get(OBJECTS_DIR, objectId);

        if (!Files.exists(objectDirectory)) {
            Files.createDirectories(objectDirectory);
        }

        Path chunkPath = objectDirectory.resolve(StorageConstants.CHUNK_PREFIX + chunk.getChunkNumber());

        Files.write(chunkPath, chunk.getData());

        log.info(
                "Stored chunk {} for object {}",
                chunk.getChunkNumber(),
                objectId
        );
    }

    public byte[] loadChunk(String objectId, int chunkNumber) throws IOException {

        Path chunkPath = Paths.get(
                OBJECTS_DIR,
                objectId,
                "chunk-" + chunkNumber
        );

        if (!Files.exists(chunkPath)) {
            throw new RuntimeException("Chunk not found");
        }

        return Files.readAllBytes(chunkPath);
    }

    public void deleteObject(String objectId) throws IOException {

        Path objectDirectory = Paths.get(
                StorageConstants.OBJECTS_DIRECTORY,
                objectId
        );

        if (!Files.exists(objectDirectory)) {
            throw new RuntimeException("Object not found");
        }

        Files.walk(objectDirectory)
                .sorted((a, b) -> b.compareTo(a))
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        log.info("Deleted object {}", objectId);
    }

}
