package com.nimbus.gateway.controller;

import com.nimbus.gateway.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UploadController {
    private final UploadService uploadService;
    @PostMapping("/upload")
    public ResponseEntity<Map<String,String>> uploadFile(
            @RequestParam("file")MultipartFile file
            ){
        uploadService.uploadFile(file);

        return ResponseEntity.ok(
                Map.of("message", "File received successfully")
        );
    }
}
