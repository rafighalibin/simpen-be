package com.nakahama.simpenbackend.Kelas.repository;

import com.nakahama.simpenbackend.Kelas.model.*;
import java.util.List;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface KelasDb extends JpaRepository<Kelas, Integer> {
    Optional<Kelas> findByKelasId(int id);

    @Query("FROM Kelas WHERE isDeleted = false")
    List<Kelas> findAll();
}
