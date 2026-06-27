package com.nimbus.storage.controller;

import com.nimbus.storage.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/objects")
@RequiredArgsConstructor
public class StorageController {
    private final StorageService storageService;


    @PostMapping("/{objectId}")
    public ResponseEntity<Map<String,String>> storeObject(
            @PathVariable String objectId,
            @RequestParam("file")MultipartFile file
            )throws Exception{
        storageService.store(objectId,file);

        return ResponseEntity.ok(
                Map.of("message","File stored successfully")
        );
    }
}
