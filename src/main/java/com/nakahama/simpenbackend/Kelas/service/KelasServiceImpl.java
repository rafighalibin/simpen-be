package com.nakahama.simpenbackend.Kelas.service;

import java.util.*;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.dto.Kelas.CreateKelasRequestDTO;
import com.nakahama.simpenbackend.Kelas.dto.Kelas.KelasMapper;
import com.nakahama.simpenbackend.Kelas.model.JenisKelas;
import com.nakahama.simpenbackend.Kelas.model.Kelas;
import com.nakahama.simpenbackend.Kelas.model.Program;
import com.nakahama.simpenbackend.Kelas.model.SesiKelas;
import com.nakahama.simpenbackend.Kelas.repository.KelasDb;
import com.nakahama.simpenbackend.User.model.Pengajar;
import com.nakahama.simpenbackend.User.service.UserService;

@Service
public class KelasServiceImpl implements KelasService {

    @Autowired
    KelasDb kelasDb;

    @Autowired
    ProgramService programService;

    @Autowired
    JenisKelasService jenisKelasService;

    @Autowired
    UserService userService;

    @Autowired
    SesiKelasService sesiKelasService;

    @Override
    public List<Kelas> getAll() {
        return kelasDb.findAll();
    }

    @Override
    public Kelas save(CreateKelasRequestDTO request) {

        Program program = programService.getById(request.getProgramId());
        JenisKelas jenisKelas = jenisKelasService.getById(request.getJenisKelasId());
        Pengajar pengajar = userService.getUserById(request.getPengajarId()).getPengajar();

        Kelas createdKelas = KelasMapper.toEntity(request, program, jenisKelas, pengajar, generateKelasId());

        kelasDb.save(createdKelas);

        for (LocalDateTime e : request.getJadwalKelas()) {
            SesiKelas sesiKelas = new SesiKelas();
            sesiKelas.setKelas(createdKelas);
            sesiKelas.setPengajar(pengajar);
            sesiKelas.setPlatform(request.getPlatform());
            sesiKelas.setWaktuPelaksanaan(e);
            sesiKelas.setStatus("Scheduled");
            sesiKelasService.save(sesiKelas);
            createdKelas.getListsesiKelas().add(sesiKelas);
        }

        return kelasDb.save(createdKelas);
    }

    private int generateKelasId() {
        List<Kelas> listKelas = kelasDb.findAll();
        if (listKelas.isEmpty()) {
            return 1;
        }
        return listKelas.size() + 1;
    }

    @Override
    public Optional<Kelas> getById(int id) {
        return kelasDb.findById(id);
    }

    @Override
    public void delete(int id) {
        kelasDb.deleteById(id);
    }
}
