package com.nakahama.simpenbackend.Kelas.repository.JenisKelas;

import com.nakahama.simpenbackend.Kelas.model.*;

import jakarta.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ModaPertemuanDb extends JpaRepository<ModaPertemuan, String> {

}
