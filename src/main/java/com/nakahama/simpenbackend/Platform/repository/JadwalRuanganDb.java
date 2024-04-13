package com.nakahama.simpenbackend.Platform.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nakahama.simpenbackend.Platform.model.JadwalRuangan;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface JadwalRuanganDb extends JpaRepository<JadwalRuangan, UUID> {

}
