package com.nakahama.simpenbackend.User.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nakahama.simpenbackend.User.model.Pengajar;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface PengajarDb extends JpaRepository<Pengajar, Long> {
    @Modifying
    @Query("DELETE FROM Pengajar a WHERE a.id = :id")
    void deleteById(@Param("id") Long id);
}
