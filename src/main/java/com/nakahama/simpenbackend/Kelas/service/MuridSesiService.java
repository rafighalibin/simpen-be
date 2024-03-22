package com.nakahama.simpenbackend.Kelas.service;

import java.util.List;
import java.util.UUID;

import com.nakahama.simpenbackend.Kelas.model.MuridKelas;
import com.nakahama.simpenbackend.Kelas.model.MuridSesi;
import com.nakahama.simpenbackend.Kelas.model.SesiKelas;

public interface MuridSesiService {

    public List<MuridSesi> getAll();

    public MuridSesi save(MuridSesi murisSesi);

    public MuridSesi getById(UUID id);

    public void delete(UUID id);

    public List<MuridSesi> createListMuridSesi(List<MuridKelas> listMurid, SesiKelas sesiKelas);

}
