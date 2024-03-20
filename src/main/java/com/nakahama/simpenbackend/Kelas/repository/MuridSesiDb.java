package com.nakahama.simpenbackend.Kelas.repository;

import com.nakahama.simpenbackend.Kelas.model.*;

import jakarta.transaction.Transactional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface MuridSesiDb extends JpaRepository<MuridSesi, UUID> {
    Optional<MuridSesi> findByMuridAndSesiKelas(Murid murid, SesiKelas sesiKelas);
}
