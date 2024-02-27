package com.nakahama.simpenbackend.Kelas.repository;

import com.nakahama.simpenbackend.Kelas.model.*;
import com.nakahama.simpenbackend.User.model.UserModel; // need to change after Pengajar model is created

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface SesiKelasDb extends JpaRepository<SesiKelas, UUID>{
    Optional<SesiKelas> findById(UUID id);

    Optional<SesiKelas> findByKelas(Kelas kelas);

    Optional<SesiKelas> findByPengajar(UserModel pengajar);

    Optional<SesiKelas> findByPengajarPengganti(UserModel pengajar_pengganti);

    Optional<SesiKelas> findByWaktuPelaksanaan(String waktu_pelaksanaan);

    Optional<SesiKelas> findByStatus(String status);

    Optional<SesiKelas> findByPlatform(String platform);
}
