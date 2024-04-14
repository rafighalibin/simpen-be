package com.nakahama.simpenbackend.Kelas.repository.JenisKelas;

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
public interface JenisKelasDb extends JpaRepository<JenisKelas, UUID> {

    Optional<JenisKelas> findById(UUID id);

    List<JenisKelas> findAllByNama(String nama);

    Optional<JenisKelas> findByProgram(Program program);

    List<JenisKelas> findByTipe(String tipe);

    List<JenisKelas> findByBahasa(String bahasa);

    List<JenisKelas> findByModaPertemuan(String modaPertemuan);

    @Query("SELECT DISTINCT jk.modaPertemuan FROM JenisKelas jk WHERE jk.nama = :nama")
    List<String> findDistinctModaPertemuanByNama(@Param("nama") String nama);

    @Query("SELECT DISTINCT jk.bahasa FROM JenisKelas jk WHERE jk.nama = :nama")
    List<String> findDistinctBahasaByNama(@Param("nama") String nama);

    @Query("SELECT DISTINCT jk.tipe FROM JenisKelas jk WHERE jk.nama = :nama")
    List<String> findDistinctTipeByNama(@Param("nama") String nama);
}
