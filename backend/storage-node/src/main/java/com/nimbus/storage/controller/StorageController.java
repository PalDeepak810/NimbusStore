package com.nimbus.storage.controller;

import com.nimbus.storage.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
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

    @GetMapping("/{objectId}")
    public ResponseEntity<Resource> downloadObject(
           @PathVariable String objectId
    )throws Exception{
        Resource resource = storageService.load(objectId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""+resource.getFilename()+"\"")
                .body(resource);
    }
}
