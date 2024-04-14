package com.nakahama.simpenbackend.Payroll.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.nakahama.simpenbackend.Payroll.dto.createAbsenPengajarDTO;
import com.nakahama.simpenbackend.Payroll.model.AbsenPengajar;
import com.nakahama.simpenbackend.Payroll.model.PeriodePayroll;

public interface AbsenPengajarService {
    public AbsenPengajar save(AbsenPengajar absenPengajar);

    public AbsenPengajar getById(UUID id);

    public AbsenPengajar createAbsen(createAbsenPengajarDTO absenPengajarDTO);

    public List<AbsenPengajar> getAllAbsenPengajar();

    public PeriodePayroll getCurrentPeriodePayroll(LocalDateTime date);    
}
