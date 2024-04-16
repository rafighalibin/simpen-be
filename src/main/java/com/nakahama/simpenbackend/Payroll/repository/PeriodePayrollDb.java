package com.nakahama.simpenbackend.Payroll.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nakahama.simpenbackend.Payroll.model.PeriodePayroll;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface PeriodePayrollDb extends JpaRepository<PeriodePayroll, Integer>{
    PeriodePayroll findByTanggalMulaiAndTanggalSelesai(Date tanggalMulai, Date tanggalSelesai);
}
