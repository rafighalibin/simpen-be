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
import java.util.List;

@Repository
@Transactional
public interface MuridKelasDb extends JpaRepository<MuridKelas, UUID> {
    Optional<MuridKelas> findById(UUID id);

    List<MuridKelas> findAllByMuridAndKelas(Murid murid, Kelas kelas);

    Optional<MuridKelas> findByKelas(Kelas kelas);

    Optional<MuridKelas> findByLinkReport(String linkReport);

    @Modifying
    @Query("DELETE FROM MuridKelas a WHERE a.muridKelasId = :muridKelasId")
    void deleteById(@Param("muridKelasId") UUID id);
}
