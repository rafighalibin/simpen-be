package com.nakahama.simpenbackend.Kelas.repository.JenisKelas;

import com.nakahama.simpenbackend.Kelas.model.*;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BahasaDb extends JpaRepository<Bahasa, String> {

}
