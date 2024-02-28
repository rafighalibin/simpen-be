package com.nakahama.simpenbackend.Kelas.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.nakahama.simpenbackend.Kelas.model.SesiKelas;
import com.nakahama.simpenbackend.Kelas.model.Kelas;
import com.nakahama.simpenbackend.User.model.UserModel;

public interface SesiKelasService {

    public List<SesiKelas> getAll();

    public SesiKelas save(SesiKelas jenisKelas);

    public Optional<SesiKelas> getById(UUID id);

    public void delete(UUID id);

    public Optional<SesiKelas> getByKelas(Kelas kelas);

    public Optional<SesiKelas> getByPengajar(UserModel pengajar);

    public Optional<SesiKelas> getByPengajarPengganti(UserModel pengajarPengganti);

    public Optional<SesiKelas> getByWaktuPermintaanBetween(Date startDate, Date endDate);

    public Optional<SesiKelas> getByStatus(String status);

    public Optional<SesiKelas> getByPlatform(String platform);
}
