package com.nakahama.simpenbackend.Kelas.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.model.Murid;
import com.nakahama.simpenbackend.Kelas.repository.MuridDb;

@Service
public class MuridServiceImpl implements MuridService{
    
    @Autowired
    MuridDb muridDb;

    @Override
    public List<Murid> getAll() {
        return muridDb.findAll();
    }

    @Override
    public Murid save(Murid murid) {
        return muridDb.save(murid);
    }

    @Override
    public Optional<Murid> getById(UUID id) {
        return muridDb.findById(id);
    }

    @Override
    public void delete(UUID id) {
        muridDb.deleteById(id);
    }

    @Override
    public Optional<Murid> getByNama(String nama) {
        return muridDb.findByNama(nama);
    }

    @Override
    public Optional<Murid> getByNamaOrtu(String namaOrtu) {
        return muridDb.findByNamaOrtu(namaOrtu);
    }

    @Override
    public Optional<Murid> getByEmailOrtu(String emailOrtu) {
        return muridDb.findByEmailOrtu(emailOrtu);
    }

    @Override
    public Optional<Murid> getByNoHpOrtu(String noHpOrtu) {
        return muridDb.findByNoHpOrtu(noHpOrtu);
    }
}
