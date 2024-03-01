package com.nakahama.simpenbackend.Kelas.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.model.Kelas;
import com.nakahama.simpenbackend.Kelas.repository.KelasDb;

@Service
public class KelasServiceImpl implements KelasService{
    
    @Autowired
    KelasDb kelasDb;

    @Override
    public List<Kelas> getAll() {
        return kelasDb.findAll();
    }

    @Override
    public Kelas save(Kelas kelas) {
        return kelasDb.save(kelas);
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
