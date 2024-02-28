package com.nakahama.simpenbackend.Kelas.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.model.*;
import com.nakahama.simpenbackend.Kelas.repository.SesiKelasDb;
import com.nakahama.simpenbackend.User.model.UserModel; // need to change after Pengajar model is created

@Service
public class SesiKelasServiceImpl implements SesiKelasService {

    @Autowired
    SesiKelasDb jenisKelasDb;

    @Override
    public List<SesiKelas> getAll() {
        return jenisKelasDb.findAll();
    }

    @Override
    public SesiKelas save(SesiKelas jenisKelas) {
        return jenisKelasDb.save(jenisKelas);
    }

    @Override
    public Optional<SesiKelas> getById(UUID id) {
        return jenisKelasDb.findById(id);
    }

    @Override
    public void delete(UUID id) {
        jenisKelasDb.deleteById(id);
    }

    @Override
    public Optional<SesiKelas> getByKelas(Kelas kelas) {
        return jenisKelasDb.findByKelas(kelas);
    }

    @Override
    public Optional<SesiKelas> getByPengajar(UserModel pengajar) {
        return jenisKelasDb.findByPengajar(pengajar);
    }

    @Override
    public Optional<SesiKelas> getByPengajarPengganti(UserModel pengajarPengganti) {
        return jenisKelasDb.findByPengajarPengganti(pengajarPengganti);
    }

    @Override
    public Optional<SesiKelas> getByWaktuPermintaanBetween(Date startDate, Date endDate) {
        return jenisKelasDb.findByWaktuPermintaanBetween(startDate, endDate);
    }

    @Override
    public Optional<SesiKelas> getByStatus(String status) {
        return jenisKelasDb.findByStatus(status);
    }

    @Override
    public Optional<SesiKelas> getByPlatform(String platform) {
        return jenisKelasDb.findByPlatform(platform);
    }
}
