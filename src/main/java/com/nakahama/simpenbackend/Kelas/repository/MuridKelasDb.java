package com.nakahama.simpenbackend.Kelas.repository;

import com.nakahama.simpenbackend.Kelas.model.*;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface MuridKelasDb extends JpaRepository<MuridKelas, UUID>{
    Optional<MuridKelas> findById(UUID id);
    Optional<MuridKelas> findByMurid(Murid murid);
    Optional<MuridKelas> findByKelas(Kelas kelas);
    Optional<MuridKelas> findByLinkReport(String linkReport);
}
