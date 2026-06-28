package com.nimbus.gateway.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ObjectMetadata {

    private UUID id;

    private String filename;

    private Long size;

    private String contentType;

    private String status;

    private LocalDateTime createdAt;

    private Integer chunkCount;
}