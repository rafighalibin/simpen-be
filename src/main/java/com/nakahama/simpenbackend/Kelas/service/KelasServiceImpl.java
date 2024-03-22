package com.nakahama.simpenbackend.Kelas.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.dto.Kelas.CreateKelas;
import com.nakahama.simpenbackend.Kelas.dto.Kelas.KelasMapper;
import com.nakahama.simpenbackend.Kelas.dto.Kelas.UpdateKelas;
import com.nakahama.simpenbackend.Kelas.dto.Kelas.UpdateKelasPlaylist;
import com.nakahama.simpenbackend.Kelas.model.JenisKelas;
import com.nakahama.simpenbackend.Kelas.model.Kelas;
import com.nakahama.simpenbackend.Kelas.model.MuridKelas;
import com.nakahama.simpenbackend.Kelas.model.Program;
import com.nakahama.simpenbackend.Kelas.model.SesiKelas;
import com.nakahama.simpenbackend.Kelas.repository.KelasDb;
import com.nakahama.simpenbackend.User.model.Pengajar;
import com.nakahama.simpenbackend.User.model.UserModel;
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

    @Autowired
    MuridKelasService muridKelasService;

    @Override
    public List<Kelas> getAll() {
        return kelasDb.findAll();
    }

    @Override
    public List<Kelas> getAllKelasPengajar(UserModel userModel) {
        return kelasDb.findAllByPengajar((Pengajar) userModel);
    }

    @Override
    public Kelas save(CreateKelas request) {

        Program program = programService.getById(request.getProgramId());
        JenisKelas jenisKelas = jenisKelasService.getById(request.getJenisKelasId());
        Pengajar pengajar = (Pengajar) userService.getUserById(request.getPengajarId());

        Kelas createdKelas = KelasMapper.toEntity(request, program, jenisKelas,
                pengajar, generateKelasId());
        kelasDb.save(createdKelas);

        List<MuridKelas> listMurid = muridKelasService.createListMuridKelas(request.getListMurid(), createdKelas);
        createdKelas.setMuridKelas(listMurid);
        kelasDb.save(createdKelas);

        List<SesiKelas> listSesiKelas = sesiKelasService.createListSesiKelas(request.getJadwalKelas(), createdKelas,
                pengajar, listMurid, request.getPlatform());
        createdKelas.setListsesiKelas(listSesiKelas);
        return kelasDb.save(createdKelas);

    }

    private int generateKelasId() {
        List<Kelas> listKelas = kelasDb.findAll();
        if (listKelas.isEmpty()) {
            return 1000;
        }
        return 1000 + listKelas.size() + 1;
    }

    @Override
    public Kelas getById(int id) {
        Kelas kelas = kelasDb.findById(id).get();
        if (kelas == null) {
            throw new NoSuchElementException("Kelas with id " + id + " not found");
        }
        if (kelas.getIsDeleted()) {
            throw new NoSuchElementException("Kelas with id " + id + " is deleted");
        }
        return kelas;
    }

    @Override
    public void delete(int id) {
        Kelas kelas = getById(id);
        kelas.setIsDeleted(true);
        kelasDb.save(kelas);
    }

    @Override
    public Kelas update(UpdateKelas kelasRequest) {

        Kelas updatedKelas = kelasDb.findById(kelasRequest.getId()).get();
        Pengajar pengajar = (Pengajar) userService.getUserById(kelasRequest.getPengajarId());
        List<MuridKelas> listMurid = muridKelasService.getListMurid(kelasRequest.getListMurid());

        return kelasDb.save(KelasMapper.toEntity(kelasRequest, updatedKelas, pengajar, listMurid));
    }

    @Override
    public void updateKelasPlaylist(UpdateKelasPlaylist updateKelasPlaylist) {
        Kelas kelas = getById(updateKelasPlaylist.getId());
        kelas.setLinkPlaylist(updateKelasPlaylist.getLinkPlaylist());
        kelasDb.save(kelas);
    }

}
