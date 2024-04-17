package com.nakahama.simpenbackend.Payroll.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nakahama.simpenbackend.Payroll.model.AbsenPengajar;
import com.nakahama.simpenbackend.Payroll.model.PeriodePayroll;
import com.nakahama.simpenbackend.User.model.Pengajar;

import jakarta.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface AbsenPengajarDb extends JpaRepository<AbsenPengajar, UUID>{
    List<AbsenPengajar> findByPeriodePayroll(PeriodePayroll periodePayroll);
   
    @Query("FROM AbsenPengajar WHERE pengajar = :requestedPengajar")
    List<AbsenPengajar> findAllByPengajar(@Param("requestedPengajar") Pengajar pengajar);
}
