package com.nakahama.simpenbackend.Kelas.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.dto.Murid.CreateMurid;
import com.nakahama.simpenbackend.Kelas.dto.Murid.MuridMapper;
import com.nakahama.simpenbackend.Kelas.dto.Murid.UpdateMurid;
import com.nakahama.simpenbackend.Kelas.model.Murid;
import com.nakahama.simpenbackend.Kelas.repository.MuridDb;

@Service
public class MuridServiceImpl implements MuridService {

    @Autowired
    MuridDb muridDb;

    @Override
    public List<Murid> getAll() {
        return muridDb.findAll();
    }

    @Override
    public Murid save(CreateMurid muridRequest) {
        return muridDb.save(MuridMapper.toEntity(muridRequest));
    }

    @Override
    public Murid update(UpdateMurid muridRequest) {
        Murid muridToUpdate = getById(muridRequest.getId());
        return muridDb.save(MuridMapper.toEntity(muridToUpdate, muridRequest));
    }

    @Override
    public Murid getById(UUID id) {
        Murid murid = muridDb.findById(id).get();
        if (murid == null)
            throw new NoSuchElementException("Murid with id " + id + " not found");
        return murid;
    }

    @Override
    public void delete(UUID id) {
        getById(id);
        muridDb.deleteById(id);
    }

    @Override
    public Murid getByNama(String nama) {
        Murid murid = muridDb.findByNama(nama).get();
        if (murid == null)
            throw new NoSuchElementException("Murid with name " + nama + " not found");
        return murid;
    }

    @Override
    public Murid getByNamaOrtu(String namaOrtu) {
        Murid murid = muridDb.findByNamaOrtu(namaOrtu).get();
        if (murid == null)
            throw new NoSuchElementException("Murid with parent name " + namaOrtu + " not found");
        return murid;
    }

    @Override
    public Murid getByEmailOrtu(String emailOrtu) {
        Murid murid = muridDb.findByEmailOrtu(emailOrtu).get();
        if (murid == null)
            throw new NoSuchElementException("Murid with parent email " + emailOrtu + " not found");
        return murid;
    }

    @Override
    public Murid getByNoHpOrtu(String noHpOrtu) {
        Murid murid = muridDb.findByNoHpOrtu(noHpOrtu).get();
        if (murid == null)
            throw new NoSuchElementException("Murid with parent phone number " + noHpOrtu + " not found");
        return murid;
    }
}
