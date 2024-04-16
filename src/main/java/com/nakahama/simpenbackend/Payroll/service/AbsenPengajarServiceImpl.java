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
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.model.SesiKelas;
import com.nakahama.simpenbackend.Kelas.service.SesiKelasService;
import com.nakahama.simpenbackend.Payroll.dto.createAbsenPengajarDTO;
import com.nakahama.simpenbackend.Payroll.model.AbsenPengajar;
import com.nakahama.simpenbackend.Payroll.model.FeeModel;
import com.nakahama.simpenbackend.Payroll.model.PeriodePayroll;
import com.nakahama.simpenbackend.Payroll.repository.AbsenPengajarDb;
import com.nakahama.simpenbackend.Payroll.repository.PeriodePayrollDb;
import com.nakahama.simpenbackend.User.model.Pengajar;
import com.nakahama.simpenbackend.User.model.UserModel;
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

    @Autowired
    FeeService feeService;

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
        FeeModel fee = feeService.getByProgramAndJenisKelas(sesiKelas.getKelas().getProgram().getId(), sesiKelas.getKelas().getJenisKelas().getId());
        absenPengajar.setFee(fee);
        absenPengajar.setJumlahFee(fee.getBaseFee() + (fee.getStudentMultiplier() * sesiKelas.getKelas().getJumlahMurid()));
        sesiKelasService.updateStatus(sesiKelas);
        
        absenPengajarDb.save(absenPengajar);
        periodePayroll.getListAbsenPengajar().add(absenPengajar);
        return absenPengajar;
    }

    @Override
    public List<AbsenPengajar> getAllAbsenPengajar(UserModel userModel) {
        return absenPengajarDb.findAllByPengajar((Pengajar) userModel);    
    }

    @Override
    public List<AbsenPengajar> getAll() {
        return absenPengajarDb.findAll();
    }

    @Override
    public PeriodePayroll getCurrentPeriodePayroll(LocalDateTime date) {
        int bulan = date.getMonthValue();
        int tahun = date.getYear();
        String namaBulan = "";
    
        LocalDateTime tanggalMulai;
        LocalDateTime tanggalSelesai;
    
        if (date.getDayOfMonth() <= 14) {
            namaBulan = Month.of(bulan - 1).name();
            tanggalMulai = LocalDateTime.of(tahun, bulan - 1, 15, 0, 0);
            tanggalSelesai = LocalDateTime.of(tahun, bulan, 14, 23, 59);
        } else {
            namaBulan = Month.of(bulan).name();
            tanggalMulai = LocalDateTime.of(tahun, bulan, 15, 0, 0);
            tanggalSelesai = LocalDateTime.of(tahun, bulan + 1, 14, 23, 59);
        }
    
        Date mulaiDate = Timestamp.valueOf(tanggalMulai);
        Date selesaiDate = Timestamp.valueOf(tanggalSelesai);
    
        PeriodePayroll periodePayroll = periodePayrollDb.findByTanggalMulaiAndTanggalSelesai(mulaiDate, selesaiDate);
    
        if (periodePayroll == null) {
            // Jika tidak ada periode payroll, buat periode baru
            periodePayroll = new PeriodePayroll();
            periodePayroll.setTanggalMulai(mulaiDate);
            periodePayroll.setTanggalSelesai(selesaiDate);
            // Tentukan tanggal pembayaran setiap bulan pada tanggal 25
            LocalDateTime tanggalPembayaran = LocalDateTime.of(tahun, bulan, 25, 0, 0);
            periodePayroll.setTanggalPembayaran(Timestamp.valueOf(tanggalPembayaran));
            periodePayroll.setListAbsenPengajar(new ArrayList<AbsenPengajar>());
            // Simpan periode payroll baru ke dalam database
            periodePayrollDb.save(periodePayroll);
        }
    
        return periodePayroll;
    }
    
}
