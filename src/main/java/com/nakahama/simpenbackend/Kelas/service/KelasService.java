package com.nakahama.simpenbackend.Kelas.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.model.Kelas;
import com.nakahama.simpenbackend.Kelas.repository.KelasDb;

@Service
public class KelasService {

    @Autowired
    KelasDb kelasDb;

    public List<Kelas> getAll() {
        return kelasDb.findAll();
    }

    public Kelas save(Kelas kelas) {
        return kelasDb.save(kelas);
    }

    public Optional<Kelas> getById(int id) {
        return kelasDb.findById(id);
    }

    public void delete(int id) {
        kelasDb.deleteById(id);
    }
}
