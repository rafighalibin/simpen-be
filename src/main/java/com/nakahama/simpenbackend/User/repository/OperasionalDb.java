package com.nakahama.simpenbackend.User.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nakahama.simpenbackend.User.model.Operasional;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface OperasionalDb extends JpaRepository<Operasional, UUID> {
    @Modifying
    @Query("DELETE FROM Operasional a WHERE a.id = :id")
    void deleteById(@Param("id") UUID id);
}
