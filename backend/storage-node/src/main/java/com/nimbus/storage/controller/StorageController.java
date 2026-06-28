package com.nimbus.storage.controller;

import com.nimbus.storage.model.Chunk;
import com.nimbus.storage.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/objects")
@RequiredArgsConstructor
public class StorageController {
    private final StorageService storageService;


    @PostMapping("/{objectId}/chunks")
    public ResponseEntity<Map<String, String>> storeChunk(
            @PathVariable String objectId,
            @RequestBody Chunk chunk) throws IOException {

        storageService.storeChunk(objectId, chunk);

        return ResponseEntity.ok(
                Map.of("message", "Chunk stored successfully")
        );
    }

    @GetMapping("/{objectId}/chunks/{chunkNumber}")
    public ResponseEntity<byte[]> getChunk(
            @PathVariable String objectId,
            @PathVariable int chunkNumber) throws IOException {

        byte[] chunk = storageService.loadChunk(objectId, chunkNumber);

        return ResponseEntity.ok(chunk);
    }
}
