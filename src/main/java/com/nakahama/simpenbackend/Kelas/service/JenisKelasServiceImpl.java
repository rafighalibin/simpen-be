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
    public JenisKelas getByNamaAndPertemuanAndTipeAndBahasa(String nama, String pertemuan, String tipe, String bahasa) {
        return jenisKelasDb.findByNamaAndModaPertemuanAndTipeAndBahasa(nama, pertemuan, tipe, bahasa);
    }

    @Override
    public Optional<JenisKelas> getByProgram(Program program_id) {
        return jenisKelasDb.findByProgram(program_id);
    }

    @Override
    public JenisKelas update(JenisKelas jenisKelas) {
        JenisKelas existingJenisKelas = jenisKelasDb.findById(jenisKelas.getId()).orElse(null);
        existingJenisKelas.setNama(jenisKelas.getNama());
        existingJenisKelas.setModaPertemuan(jenisKelas.getModaPertemuan());
        existingJenisKelas.setTipe(jenisKelas.getTipe());
        existingJenisKelas.setBahasa(jenisKelas.getBahasa());
        existingJenisKelas.setProgram(jenisKelas.getProgram());

        return jenisKelasDb.save(existingJenisKelas);
    }

    @Override
    public List<String> findDistinctModaPertemuan() {
        return jenisKelasDb.findDistinctModaPertemuan();
    }
}
