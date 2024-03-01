package com.nakahama.simpenbackend.Kelas.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.nakahama.simpenbackend.Kelas.model.Kelas;
import com.nakahama.simpenbackend.Kelas.model.Murid;
import com.nakahama.simpenbackend.Kelas.model.MuridKelas;

public interface MuridKelasService {
    
    public List<MuridKelas> getAll();

    public MuridKelas save(MuridKelas muridKelas);

    public Optional<MuridKelas> getById(UUID id);

    public void delete(UUID id);

    public Optional<MuridKelas> getByMurid(Murid murid);

    public Optional<MuridKelas> getByKelas(Kelas kelas);
    
    public Optional<MuridKelas> getByLinkReport(String linkReport);

}
