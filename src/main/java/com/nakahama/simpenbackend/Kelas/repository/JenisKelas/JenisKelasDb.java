package com.nakahama.simpenbackend.Kelas.repository.JenisKelas;

import com.nakahama.simpenbackend.Kelas.model.*;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
@Transactional
public interface JenisKelasDb extends JpaRepository<JenisKelas, UUID> {

    Optional<JenisKelas> findById(UUID id);

    List<JenisKelas> findAllByNama(String nama);

    Optional<JenisKelas> findByProgram(Program program);
}
