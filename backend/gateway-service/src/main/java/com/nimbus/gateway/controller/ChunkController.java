package com.nimbus.gateway.controller;


import com.nimbus.gateway.model.Chunk;
import com.nimbus.gateway.service.ChunkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chunks")
@RequiredArgsConstructor
public class ChunkController {

    private final ChunkService chunkService;

    @PostMapping
    public ResponseEntity<Map<String, Integer>> splitFile(
            @RequestParam("file") MultipartFile file) throws IOException {

        List<Chunk> chunks = chunkService.splitIntoChunks(file);

        return ResponseEntity.ok(
                Map.of("totalChunks", chunks.size())
        );
    }
}