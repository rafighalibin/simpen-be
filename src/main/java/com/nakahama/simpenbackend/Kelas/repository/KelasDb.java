package com.nakahama.simpenbackend.Kelas.repository;

import com.nakahama.simpenbackend.Kelas.model.*;
import com.nakahama.simpenbackend.User.model.Pengajar;

import java.util.*;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface KelasDb extends JpaRepository<Kelas, Integer> {
    Optional<Kelas> findByKelasId(int id);

    @Query("FROM Kelas WHERE isDeleted = false")
    List<Kelas> findAll();

    @Query("FROM Kelas WHERE pengajar = :requestedPengajar AND isDeleted = false")
    List<Kelas> findAllByPengajar(@Param("requestedPengajar") Pengajar pengajar);
}
