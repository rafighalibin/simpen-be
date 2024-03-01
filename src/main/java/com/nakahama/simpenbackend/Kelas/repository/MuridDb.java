package com.nakahama.simpenbackend.Kelas.repository;

import com.nakahama.simpenbackend.Kelas.model.*;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface MuridDb extends JpaRepository<Murid, UUID>{
    Optional<Murid> findById(UUID id);
    Optional<Murid> findByNama(String nama);
    Optional<Murid> findByNamaOrtu(String namaOrtu);
    Optional<Murid> findByEmailOrtu(String emailOrtu);
    Optional<Murid> findByNoHpOrtu(String noHpOrtu);
}
