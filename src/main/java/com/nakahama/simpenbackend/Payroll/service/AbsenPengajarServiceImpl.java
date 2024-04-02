package com.nakahama.simpenbackend.Payroll.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.model.SesiKelas;
import com.nakahama.simpenbackend.Kelas.service.SesiKelasService;
import com.nakahama.simpenbackend.Payroll.dto.createAbsenPengajarDTO;
import com.nakahama.simpenbackend.Payroll.model.AbsenPengajar;
import com.nakahama.simpenbackend.Payroll.repository.AbsenPengajarDb;
import com.nakahama.simpenbackend.User.model.Pengajar;
import com.nakahama.simpenbackend.User.service.UserService;
import com.nakahama.simpenbackend.exception.BadRequestException;

@Service
public class AbsenPengajarServiceImpl implements AbsenPengajarService{
    
    @Autowired
    AbsenPengajarDb absenPengajarDb;

    @Autowired
    SesiKelasService sesiKelasService;

    @Autowired
    UserService userService;

    @Override
    public AbsenPengajar save(AbsenPengajar absenPengajar) {
        return absenPengajarDb.save(absenPengajar);
    }

    @Override
    public AbsenPengajar getById(UUID id) {
        return absenPengajarDb.findById(id).get();
    }

    @Override
    public AbsenPengajar createAbsen(createAbsenPengajarDTO absenPengajarDTO) {
        SesiKelas sesiKelas = sesiKelasService.getById(absenPengajarDTO.getIdSesiKelas());
        if(absenPengajarDTO.getPengajar().getId() != sesiKelas.getPengajar().getId()){
            throw new BadRequestException("Pengajar hanya boleh absen pada sesi kelas yang dia ajar");
        }
        AbsenPengajar absenPengajar = new AbsenPengajar();
        absenPengajar.setPengajar(absenPengajarDTO.getPengajar());
        absenPengajar.setSesiKelas(sesiKelas);
        absenPengajar.setKelas(sesiKelas.getKelas());
        sesiKelasService.updateStatus(sesiKelas);
        return absenPengajarDb.save(absenPengajar);
    }
    
}
