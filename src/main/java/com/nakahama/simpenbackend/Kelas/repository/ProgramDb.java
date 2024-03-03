package com.nakahama.simpenbackend.Kelas.repository;

import com.nakahama.simpenbackend.Kelas.model.*;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface ProgramDb extends JpaRepository<Program, UUID> {

    Optional<Program> findById(UUID id);

    Optional<Program> findByNama(String nama);
}
