package com.nakahama.simpenbackend.Payroll.service;

import java.util.UUID;

import com.nakahama.simpenbackend.Payroll.dto.createAbsenPengajarDTO;
import com.nakahama.simpenbackend.Payroll.model.AbsenPengajar;

public interface AbsenPengajarService {
    public AbsenPengajar save(AbsenPengajar absenPengajar);

    public AbsenPengajar getById(UUID id);

    public AbsenPengajar createAbsen(createAbsenPengajarDTO absenPengajarDTO);
    
}
