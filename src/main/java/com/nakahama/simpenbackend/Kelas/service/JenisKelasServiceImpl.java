package com.nakahama.simpenbackend.Kelas.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.model.*;
import com.nakahama.simpenbackend.Kelas.repository.JenisKelasDb;

@Service
public class JenisKelasServiceImpl implements JenisKelasService {

    @Autowired
    JenisKelasDb jenisKelasDb;

    @Override
    public List<JenisKelas> getAll() {
        return jenisKelasDb.findAll();
    }

    @Override
    public JenisKelas save(JenisKelas jenisKelas) {
        return jenisKelasDb.save(jenisKelas);
    }

    @Override
    public Optional<JenisKelas> getById(UUID id) {
        return jenisKelasDb.findById(id);
    }

    @Override
    public void delete(UUID id) {
        jenisKelasDb.deleteById(id);
    }

    @Override
    public Optional<JenisKelas> getByNama(String nama) {
        return jenisKelasDb.findByNama(nama);
    }

    @Override
    public JenisKelas getByNamaAndPertemuanAndTipeAndBahasa(String nama, int pertemuan, String tipe, String bahasa) {
        return jenisKelasDb.findByNamaAndPertemuanAndTipeAndBahasa(nama, pertemuan, tipe, bahasa);
    }

    @Override
    public Optional<JenisKelas> getByProgram(Program program_id) {
        return jenisKelasDb.findByProgram(program_id);
    }
}
