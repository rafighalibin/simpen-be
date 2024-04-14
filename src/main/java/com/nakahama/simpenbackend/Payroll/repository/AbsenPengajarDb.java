package com.nakahama.simpenbackend.Payroll.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nakahama.simpenbackend.Payroll.model.AbsenPengajar;
import com.nakahama.simpenbackend.Payroll.model.PeriodePayroll;

import jakarta.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface AbsenPengajarDb extends JpaRepository<AbsenPengajar, UUID>{
    List<AbsenPengajar> findByPeriodePayroll(PeriodePayroll periodePayroll);
}
