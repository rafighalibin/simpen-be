package com.nakahama.simpenbackend.Platform.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nakahama.simpenbackend.Platform.model.Ruangan;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface RuanganDb extends JpaRepository<Ruangan, UUID> {
    @Modifying
    @Query("DELETE FROM Pengajar a WHERE a.id = :id")
    void deleteById(@Param("id") UUID id);

    List<Ruangan> findByNamaAndCabang(String nama, String cabang);
}
