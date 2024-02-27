package com.nakahama.simpenbackend.Kelas.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.model.*;
import com.nakahama.simpenbackend.Kelas.repository.SesiKelasDb;
import com.nakahama.simpenbackend.User.model.UserModel; // need to change after Pengajar model is created

@Service
public class SesiKelasService {

    @Autowired
    SesiKelasDb jenisKelasDb;

    public List<SesiKelas> getAll() {
        return jenisKelasDb.findAll();
    }

    public SesiKelas save(SesiKelas jenisKelas) {
        return jenisKelasDb.save(jenisKelas);
    }

    public Optional<SesiKelas> getById(UUID id) {
        return jenisKelasDb.findById(id);
    }

    public void delete(UUID id) {
        jenisKelasDb.deleteById(id);
    }

    public Optional<SesiKelas> getByKelas(Kelas kelas) {
        return jenisKelasDb.findByKelas(kelas);
    }

    public Optional<SesiKelas> getByPengajar(UserModel pengajar) {
        return jenisKelasDb.findByPengajar(pengajar);
    }

    public Optional<SesiKelas> getByPengajarPengganti(UserModel pengajarPengganti) {
        return jenisKelasDb.findByPengajarPengganti(pengajarPengganti);
    }

    public Optional<SesiKelas> getByWaktuPermintaanBetween(Date startDate, Date endDate) {
        return jenisKelasDb.findByWaktuPermintaanBetween(startDate, endDate);
    }

    public Optional<SesiKelas> getByStatus(String status) {
        return jenisKelasDb.findByStatus(status);
    }

    public Optional<SesiKelas> getByPlatform(String platform) {
        return jenisKelasDb.findByPlatform(platform);
    }
}
