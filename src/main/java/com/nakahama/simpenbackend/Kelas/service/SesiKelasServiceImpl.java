package com.nakahama.simpenbackend.Kelas.service;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.model.*;
import com.nakahama.simpenbackend.Kelas.repository.SesiKelasDb;
import com.nakahama.simpenbackend.User.model.Pengajar;
import com.nakahama.simpenbackend.User.model.UserModel; // need to change after Pengajar model is created
import com.nakahama.simpenbackend.User.service.UserService;

@Service
public class SesiKelasServiceImpl implements SesiKelasService {

    @Autowired
    SesiKelasDb sesiKelasDb;

    @Autowired
    @Lazy
    KelasService kelasService;

    @Autowired
    UserService userService;

    @Autowired
    @Lazy
    MuridSesiService muridSesiService;

    @Override
    public List<SesiKelas> getAll() {
        return sesiKelasDb.findAll();
    }

    @Override
    public SesiKelas save(SesiKelas sesiKelas) {
        return sesiKelasDb.save(sesiKelas);
    }

    @Override
    public SesiKelas getById(UUID id) {
        SesiKelas sesiKelas = sesiKelasDb.findById(id).get();
        if (sesiKelas == null)
            throw new NoSuchElementException("Sesi Kelas with id " + id + " not found");
        return sesiKelas;
    }

    @Override
    public void delete(UUID id) {
        sesiKelasDb.deleteById(id);
    }

    @Override
    public List<SesiKelas> getByKelasId(int idKelas) {
        Kelas kelas = kelasService.getById(idKelas);
        List<SesiKelas> sesiKelas = sesiKelasDb.findAllByKelas(kelas);
        if (sesiKelas == null)
            throw new NoSuchElementException("Sesi Kelas with kelas " + kelas + " not found");
        return sesiKelas;
    }

    @Override
    public List<SesiKelas> getByPengajarId(UUID idPengajar) {
        UserModel pengajar = userService.getUserById(idPengajar);
        List<SesiKelas> sesiKelas = sesiKelasDb.findAllByPengajar(pengajar);
        if (sesiKelas == null)
            throw new NoSuchElementException("Sesi Kelas with pengajar " + pengajar + " not found");
        return sesiKelas;
    }

    @Override
    public SesiKelas getByPengajarPenggantiId(UUID idPengajarPengganti) {
        UserModel pengajar = userService.getUserById(idPengajarPengganti);
        SesiKelas sesiKelas = sesiKelasDb.findByPengajarPengganti(pengajar).get();
        if (sesiKelas == null)
            throw new NoSuchElementException("Sesi Kelas with pengajar pengganti " + pengajar + " not found");
        return sesiKelas;
    }

    @Override
    public SesiKelas getByWaktuPermintaanBetween(Date startDate, Date endDate) {
        SesiKelas sesiKelas = sesiKelasDb.findByWaktuPermintaanBetween(startDate, endDate).get();
        if (sesiKelas == null)
            throw new NoSuchElementException(
                    "Sesi Kelas with waktu permintaan between " + startDate + " and " + endDate + " not found");
        return sesiKelas;
    }

    @Override
    public SesiKelas getByStatus(String status) {
        SesiKelas sesiKelas = sesiKelasDb.findByStatus(status).get();
        if (sesiKelas == null)
            throw new NoSuchElementException(
                    "Sesi Kelas with status " + status + " not found");
        return sesiKelas;
    }

    @Override
    public SesiKelas getByPlatformId(String platform) {
        SesiKelas sesiKelas = sesiKelasDb.findByStatus(platform).get();
        if (sesiKelas == null)
            throw new NoSuchElementException(
                    "Sesi Kelas with platform " + platform + " not found");
        return sesiKelas;
    }

    @Override
    public List<SesiKelas> createListSesiKelas(List<LocalDateTime> jadwalKelas, Kelas createdKelas, Pengajar pengajar,
            List<MuridKelas> listMurid, String platform) {
        List<SesiKelas> listSesiKelas = new ArrayList<>();
        for (LocalDateTime e : jadwalKelas) {
            SesiKelas sesiKelas = new SesiKelas();

            sesiKelas.setKelas(createdKelas);
            sesiKelas.setPengajar(pengajar);
            sesiKelas.setPlatform(platform);
            sesiKelas.setWaktuPelaksanaan(e);
            sesiKelas.setStatus("Scheduled");
            save(sesiKelas);
            listSesiKelas.add(sesiKelas);

            sesiKelas.setListMuridSesi(muridSesiService.createListMuridSesi(listMurid, sesiKelas));
            save(sesiKelas);

        }
        return listSesiKelas;
    }

}
