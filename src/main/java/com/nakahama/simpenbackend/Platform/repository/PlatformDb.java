package com.nakahama.simpenbackend.Platform.repository;

import com.nakahama.simpenbackend.Platform.model.Platform;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface PlatformDb extends JpaRepository<Platform, UUID> {
    Optional<Platform> findById(UUID id);

    void deleteById(UUID id);
}
