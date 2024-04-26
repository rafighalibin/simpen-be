package com.nakahama.simpenbackend.Kelas.repository;

import com.nakahama.simpenbackend.Kelas.model.*;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface MuridKelasDb extends JpaRepository<MuridKelas, UUID> {
    Optional<MuridKelas> findById(UUID id);

    Optional<MuridKelas> findByMurid(Murid murid);

    Optional<MuridKelas> findByKelas(Kelas kelas);

    Optional<MuridKelas> findByLinkReport(String linkReport);

    Optional<MuridKelas> findByMuridAndKelas(Murid murid, Kelas kelas);

    @Modifying
    @Query("DELETE FROM MuridKelas m WHERE m.muridKelasId = :id")
    void deleteById(@Param("id") UUID id);
}
