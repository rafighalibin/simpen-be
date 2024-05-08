package com.nakahama.simpenbackend.Platform.repository;

import java.util.*;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nakahama.simpenbackend.Platform.model.JadwalZoom;
import com.nakahama.simpenbackend.Platform.model.Platform;
import com.nakahama.simpenbackend.Platform.model.Zoom;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface JadwalZoomDb extends JpaRepository<JadwalZoom, UUID> {

        @Query("SELECT P FROM Platform P WHERE NOT EXISTS (SELECT j FROM JadwalZoom j WHERE j.waktu BETWEEN :startTime AND :endTime AND P = j.zoom)")
        List<Platform> checkJadwal(@Param("startTime") LocalDateTime startTime,
                        @Param("endTime") LocalDateTime endTime);

        @Query("SELECT j FROM JadwalZoom j WHERE j.waktu BETWEEN :startTime AND :endTime AND j.zoom = :zoom")
        List<JadwalZoom> checkJadwalForZoom(@Param("startTime") LocalDateTime startTime,
                        @Param("endTime") LocalDateTime endTime, @Param("zoom") Zoom zoom);

        @Query("SELECT P FROM Platform P WHERE NOT EXISTS (SELECT j FROM JadwalZoom j WHERE j.sesiKelas IS NOT NULL AND NOT (j.waktu BETWEEN :startTime AND :endTime OR NOT j.sesiKelas.id = :sesiId) AND P = j.zoom) ")
        List<Platform> checkJadwalForSesi(@Param("startTime") LocalDateTime startTime,
                        @Param("endTime") LocalDateTime endTime, @Param("sesiId") UUID sesiId);

        List<JadwalZoom> findByZoom(Zoom zoom);
}
