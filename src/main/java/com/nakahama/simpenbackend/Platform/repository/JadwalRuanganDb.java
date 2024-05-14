package com.nakahama.simpenbackend.Platform.repository;

import java.time.LocalDateTime;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nakahama.simpenbackend.Platform.model.JadwalRuangan;
import com.nakahama.simpenbackend.Platform.model.Platform;
import com.nakahama.simpenbackend.Platform.model.Ruangan;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface JadwalRuanganDb extends JpaRepository<JadwalRuangan, UUID> {

    @Query("SELECT P FROM Platform P JOIN Ruangan R ON P.id = R.id WHERE NOT EXISTS (SELECT j FROM JadwalRuangan j WHERE j.waktu BETWEEN :startTime AND :endTime AND P = j.ruangan) AND R.cabang = :cabang")
    List<Platform> checkJadwal(@Param("startTime") LocalDateTime startTime,
                                @Param("endTime") LocalDateTime endTime, 
                                @Param("cabang") String cabang);

    @Query("SELECT j FROM JadwalRuangan j WHERE j.waktu BETWEEN :startTime AND :endTime AND j.ruangan = :ruangan")
    List<JadwalRuangan> checkJadwalForRuangan(@Param("startTime") LocalDateTime startTime,
                    @Param("endTime") LocalDateTime endTime, @Param("ruangan") Ruangan ruangan);

    List<JadwalRuangan> findByRuangan(Ruangan ruangan);
}
