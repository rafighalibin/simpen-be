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
    SesiKelasDb sesiKelasDb;

    @Override
    public List<SesiKelas> getAll() {
        return sesiKelasDb.findAll();
    }

    @Override
    public SesiKelas save(SesiKelas sesiKelas) {
        return sesiKelasDb.save(sesiKelas);
    }

    @Override
    public Optional<SesiKelas> getById(UUID id) {
        return sesiKelasDb.findById(id);
    }

    @Override
    public void delete(UUID id) {
        sesiKelasDb.deleteById(id);
    }

    @Override
    public Optional<SesiKelas> getByKelas(Kelas kelas) {
        return sesiKelasDb.findByKelas(kelas);
    }

    @Override
    public Optional<SesiKelas> getByPengajar(UserModel pengajar) {
        return sesiKelasDb.findByPengajar(pengajar);
    }

    @Override
    public Optional<SesiKelas> getByPengajarPengganti(UserModel pengajarPengganti) {
        return sesiKelasDb.findByPengajarPengganti(pengajarPengganti);
    }

    @Override
    public Optional<SesiKelas> getByWaktuPermintaanBetween(Date startDate, Date endDate) {
        return sesiKelasDb.findByWaktuPermintaanBetween(startDate, endDate);
    }

    @Override
    public Optional<SesiKelas> getByStatus(String status) {
        return sesiKelasDb.findByStatus(status);
    }

    @Override
    public Optional<SesiKelas> getByPlatform(String platform) {
        return sesiKelasDb.findByPlatform(platform);
    }
}
