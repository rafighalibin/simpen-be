package com.nakahama.simpenbackend.Kelas.repository;

import com.nakahama.simpenbackend.Kelas.model.*;
import com.nakahama.simpenbackend.User.model.UserModel; // need to change after Pengajar model is created

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Transactional
public interface SesiKelasDb extends JpaRepository<SesiKelas, UUID> {
    Optional<SesiKelas> findById(UUID id);

    Optional<SesiKelas> findByKelas(Kelas kelas);

    Optional<SesiKelas> findByPengajar(UserModel pengajar);

    Optional<SesiKelas> findByPengajarPengganti(UserModel pengajar_pengganti);

    @Query(value = "SELECT * FROM permintaan_pengiriman pp WHERE (pp.waktu_permintaan > :start) AND (pp.waktu_permintaan < :end)", nativeQuery = true)
    Optional<SesiKelas> findByWaktuPermintaanBetween(@Param("start") Date startDate, @Param("end") Date endDate);

    Optional<SesiKelas> findByStatus(String status);

    Optional<SesiKelas> findByPlatform(String platform);

    List<SesiKelas> findAllByPengajar(UserModel pengajar);

    List<SesiKelas> findAllByKelas(Kelas kelas);
}
