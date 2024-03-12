package com.nakahama.simpenbackend.Kelas.repository;

import com.nakahama.simpenbackend.Kelas.model.*;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
@Transactional
public interface ProgramDb extends JpaRepository<Program, UUID> {

    Optional<Program> findById(UUID id);

    Optional<Program> findByNama(String nama);

    @Query("SELECT DISTINCT jk.nama FROM Program p JOIN p.jenisKelas jk WHERE p.id = :id")
    List<String> findDistinctJenisKelasByProgramId(@Param("id") UUID id);
}
