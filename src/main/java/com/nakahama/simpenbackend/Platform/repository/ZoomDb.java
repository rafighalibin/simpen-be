package com.nakahama.simpenbackend.Platform.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nakahama.simpenbackend.Platform.model.Zoom;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ZoomDb extends JpaRepository<Zoom, UUID> {
    @Modifying
    @Query("DELETE FROM Operasional a WHERE a.id = :id")
    void deleteById(@Param("id") UUID id);

    List<Zoom> findByNama(String nama);
}
