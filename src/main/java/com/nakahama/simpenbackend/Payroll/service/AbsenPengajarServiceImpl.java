package com.nakahama.simpenbackend.Payroll.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.model.SesiKelas;
import com.nakahama.simpenbackend.Kelas.service.SesiKelasService;
import com.nakahama.simpenbackend.Payroll.dto.createAbsenPengajarDTO;
import com.nakahama.simpenbackend.Payroll.model.AbsenPengajar;
import com.nakahama.simpenbackend.Payroll.model.PeriodePayroll;
import com.nakahama.simpenbackend.Payroll.repository.AbsenPengajarDb;
import com.nakahama.simpenbackend.Payroll.repository.PeriodePayrollDb;
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

    @Autowired
    PeriodePayrollDb periodePayrollDb;

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
        absenPengajar.setTanggalUpdate(LocalDateTime.now());
        PeriodePayroll periodePayroll = getCurrentPeriodePayroll(absenPengajar.getTanggalUpdate());
        absenPengajar.setPeriodePayroll(periodePayroll);
        absenPengajar.setSesiKelas(sesiKelas);
        absenPengajar.setKelas(sesiKelas.getKelas());
        sesiKelasService.updateStatus(sesiKelas);
        
        absenPengajarDb.save(absenPengajar);
        periodePayroll.getListAbsenPengajar().add(absenPengajar);
        return absenPengajar;
    }

    @Override
    public List<AbsenPengajar> getAllAbsenPengajar() {
        return absenPengajarDb.findAll();
    }

    @Override
    public PeriodePayroll getCurrentPeriodePayroll(LocalDateTime date) {
        int bulan = date.getMonthValue();
        int tahun = date.getYear();
        String namaBulan = Month.of(bulan).name();


    // Ambil tanggal 14 dari bulan sebelumnya
    LocalDateTime tanggalMulai = LocalDateTime.of(tahun, bulan - 1, 14, 0, 0);

    // Ambil tanggal 15 dari bulan saat ini
    LocalDateTime tanggalSelesai = LocalDateTime.of(tahun, bulan, 15, 23, 59);

    Date mulaiDate = Timestamp.valueOf(tanggalMulai);
    Date selesaiDate = Timestamp.valueOf(tanggalSelesai);
        
    PeriodePayroll periodePayroll = periodePayrollDb.findByTanggalMulaiAndTanggalSelesai(mulaiDate, selesaiDate);

    if (periodePayroll == null) {
        // Jika tidak ada periode payroll, buat periode baru
        periodePayroll = new PeriodePayroll();
        periodePayroll.setBulan(namaBulan);
        periodePayroll.setTahun(tahun);
        periodePayroll.setTanggalMulai(mulaiDate);
        periodePayroll.setTanggalSelesai(selesaiDate);
        // Tentukan tanggal pembayaran sesuai kebutuhan Anda
        periodePayroll.setListAbsenPengajar(new ArrayList<AbsenPengajar>());
        periodePayroll.setTanggalPembayaran(Timestamp.valueOf(date));
        // Simpan periode payroll baru ke dalam database
        periodePayrollDb.save(periodePayroll);
    }

    return periodePayroll;
    }
    
}
