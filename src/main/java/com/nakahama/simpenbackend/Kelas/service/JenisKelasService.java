package com.nakahama.simpenbackend.Kelas.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.model.*;

@Service
public interface JenisKelasService {

    public List<JenisKelas> getAll();

    public JenisKelas save(JenisKelas jenisKelas);

    public Optional<JenisKelas> getById(UUID id);

    public void delete(UUID id);

    public Optional<JenisKelas> getByNama(String nama);

    public JenisKelas getByNamaAndPertemuanAndTipeAndBahasa(String nama, int pertemuan, String tipe, String bahasa);

    public Optional<JenisKelas> getByProgram(Program program_id);
}
