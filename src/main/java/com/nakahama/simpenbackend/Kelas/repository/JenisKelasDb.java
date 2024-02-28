package com.nakahama.simpenbackend.Kelas.repository;

import com.nakahama.simpenbackend.Kelas.model.*;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface JenisKelasDb extends JpaRepository<JenisKelas, UUID>{

    Optional<JenisKelas> findById(UUID id);

    Optional<JenisKelas> findByNama(String nama);

    JenisKelas findByNamaAndPertemuanAndTipeAndBahasa(String nama, int pertemuan, String tipe, String bahasa);

    Optional<JenisKelas> findByProgram(Program program);
}
