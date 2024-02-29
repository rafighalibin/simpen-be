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

    public List<JenisKelas> getAll() {
        return jenisKelasDb.findAll();
    }

    public JenisKelas save(JenisKelas jenisKelas) {
        return jenisKelasDb.save(jenisKelas);
    }

    public Optional<JenisKelas> getById(UUID id) {
        return jenisKelasDb.findById(id);
    }

    public void delete(UUID id) {
        jenisKelasDb.deleteById(id);
    }

    public Optional<JenisKelas> getByNama(String nama) {
        return jenisKelasDb.findByNama(nama);
    }

    public JenisKelas getByNamaAndPertemuanAndTipeAndBahasa(String nama, int pertemuan, String tipe, String bahasa) {
        return jenisKelasDb.findByNamaAndPertemuanAndTipeAndBahasa(nama, pertemuan, tipe, bahasa);
    }

    public Optional<JenisKelas> getByProgram(Program program_id) {
        return jenisKelasDb.findByProgram(program_id);
    }
}
