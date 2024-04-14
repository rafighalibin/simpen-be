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

    public void save(CreateJenisKelas jenisKelasRequest);

    public JenisKelas getById(UUID id);

    public ReadJenisKelas getJenisKelasById(UUID id);

    public void delete(UUID uuid);

    public List<JenisKelas> getByNama(String nama);

    public ReadJenisKelas update(UpdateJenisKelas jenisKelasRequest);

    public Map<String, List<String>> getAllExistingAttributes();

    public Map<String, List<String>> getExistingAttributes(String nama);

    public ReadJenisKelas findJenisKelas(String nama, String tipe, String modaPertemuan, String bahasa);
}
