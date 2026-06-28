package com.nimbus.metadata.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "objects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ObjectMetadata {
    @Id
    private UUID id;

    private String filename;

    private Long size;

    private String contentType;

    private ObjectStatus status;

    private LocalDateTime createdAt;

    private Integer chunkCount;
}
