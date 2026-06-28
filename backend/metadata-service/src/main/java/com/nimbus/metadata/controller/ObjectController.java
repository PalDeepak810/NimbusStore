package com.nimbus.metadata.controller;

import com.nimbus.metadata.entity.ObjectMetadata;
import com.nimbus.metadata.service.ObjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/objects")
@RequiredArgsConstructor
public class ObjectController {
    private final ObjectService objectService;

    @PostMapping
    public ResponseEntity<Map<String,String>> createObject(
            @RequestParam String filename,
            @RequestParam Long size,
            @RequestParam String contentType,
            @RequestParam Integer chunkCount
    ){
        UUID objectId = objectService.createObject(
                filename,
                size,
                contentType,
                chunkCount
        );

        return ResponseEntity.ok(
                Map.of("objectId",objectId.toString())
        );
    }

    @GetMapping("/{objectId}")
    public ResponseEntity<ObjectMetadata> getObject(
            @PathVariable UUID objectId) {

        ObjectMetadata object = objectService.getObject(objectId);

        return ResponseEntity.ok(object);
    }
}
