package com.nimbus.gateway.controller;

import com.nimbus.gateway.service.DownloadService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DownloadController {
    private final DownloadService downloadService;

    @GetMapping("/objects/{objectId}")
    public ResponseEntity<Resource> downloadObject(
            @PathVariable String objectId
    ){
        Resource resource = downloadService.downloadObject(objectId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""+ resource.getFilename()+"\"")
                .body(resource);
    }
}
