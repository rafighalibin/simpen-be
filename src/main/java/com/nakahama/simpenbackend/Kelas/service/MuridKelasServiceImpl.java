package com.nakahama.simpenbackend.Kelas.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.model.Kelas;
import com.nakahama.simpenbackend.Kelas.model.Murid;
import com.nakahama.simpenbackend.Kelas.model.MuridKelas;
import com.nakahama.simpenbackend.Kelas.repository.MuridKelasDb;

@Service
public class MuridKelasServiceImpl implements MuridKelasService{
    
    @Autowired
    MuridKelasDb muridKelasDb;

    @Override
    public List<MuridKelas> getAll() {
        return muridKelasDb.findAll();
    }

    @Override
    public MuridKelas save(MuridKelas muridKelas) {
        return muridKelasDb.save(muridKelas);
    }

    @Override
    public Optional<MuridKelas> getById(UUID id) {
        return muridKelasDb.findById(id);
    }

    @Override
    public void delete(UUID id) {
        muridKelasDb.deleteById(id);
    }

    @Override
    public Optional<MuridKelas> getByMurid(Murid murid) {
        return muridKelasDb.findByMurid(murid);
    }

    @Override
    public Optional<MuridKelas> getByKelas(Kelas kelas) {
        return muridKelasDb.findByKelas(kelas);
    }

    @Override
    public Optional<MuridKelas> getByLinkReport(String linkReport) {
        return muridKelasDb.findByLinkReport(linkReport);
    }
    
}
