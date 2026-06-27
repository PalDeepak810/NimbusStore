package com.nimbus.metadata.controller;

import com.nimbus.metadata.service.ObjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestParam String contentType
    ){
        UUID objectId = objectService.createObject(
                filename,
                size,
                contentType
        );

        return ResponseEntity.ok(
                Map.of("objectId",objectId.toString())
        );
    }
}
