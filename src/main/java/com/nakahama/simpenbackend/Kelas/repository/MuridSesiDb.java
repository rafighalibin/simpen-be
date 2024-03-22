package com.nakahama.simpenbackend.Kelas.repository;

import com.nakahama.simpenbackend.Kelas.model.*;

import jakarta.transaction.Transactional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface MuridSesiDb extends JpaRepository<MuridSesi, UUID> {
    Optional<MuridSesi> findByMuridAndSesiKelas(Murid murid, SesiKelas sesiKelas);

    @Modifying
    @Query("DELETE FROM MuridSesi a WHERE a.id = :id")
    void deleteById(@Param("id") UUID id);
}
