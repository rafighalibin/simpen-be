package com.nakahama.simpenbackend.Kelas.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.CreateJenisKelas;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.ReadJenisKelas;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.UpdateJenisKelas;
import com.nakahama.simpenbackend.Kelas.model.*;

@Service
public interface JenisKelasService {

    public List<ReadJenisKelas> getAll();

    public JenisKelas save(CreateJenisKelas jenisKelasRequest);

    public Optional<JenisKelas> getById(UUID id);

    public void delete(UUID uuid);

    public Optional<JenisKelas> getByNama(String nama);

    public JenisKelas getByNamaAndPertemuanAndTipeAndBahasa(String nama, String pertemuan, String tipe, String bahasa);

    public ReadJenisKelas update(UpdateJenisKelas jenisKelasRequest);
}
