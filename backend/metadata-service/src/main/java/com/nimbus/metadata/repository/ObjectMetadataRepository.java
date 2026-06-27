package com.nimbus.metadata.repository;

import com.nimbus.metadata.entity.ObjectMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ObjectMetadataRepository extends JpaRepository<ObjectMetadata, UUID> {
}
