package com.nimbus.gateway.controller;

import com.nimbus.gateway.client.MetadataClient;
import com.nimbus.gateway.model.ObjectMetadata;
import com.nimbus.gateway.service.DownloadService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DownloadController {
    private final DownloadService downloadService;

    private final MetadataClient metadataClient;

    @GetMapping("/objects/{objectId}")
    public ResponseEntity<byte[]> downloadObject(
            @PathVariable String objectId
    )throws IOException {
        ObjectMetadata metadata = metadataClient.getObject(objectId);

        byte[] file = downloadService.downloadObject(objectId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(metadata.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + metadata.getFilename() + "\"")
                .body(file);
    }
}
