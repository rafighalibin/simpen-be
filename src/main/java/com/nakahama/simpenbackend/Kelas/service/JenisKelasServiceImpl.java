package com.nakahama.simpenbackend.Kelas.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.CreateJenisKelas;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.JenisKelasMapper;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.ReadJenisKelas;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.UpdateJenisKelas;
import com.nakahama.simpenbackend.Kelas.dto.Program.ProgramMapper;
import com.nakahama.simpenbackend.Kelas.dto.Program.ReadProgram;
import com.nakahama.simpenbackend.Kelas.model.*;
import com.nakahama.simpenbackend.Kelas.repository.JenisKelasDb;
import com.nakahama.simpenbackend.User.model.UserModel;
import com.nakahama.simpenbackend.User.service.UserService;
import com.nakahama.simpenbackend.exception.BadRequestException;

@Service
public class JenisKelasServiceImpl implements JenisKelasService {

    @Autowired
    JenisKelasDb jenisKelasDb;

    @Autowired
    UserService userService;

    @Override
    public List<ReadJenisKelas> getAll() {
        List<ReadJenisKelas> listJenisKelas = new ArrayList<ReadJenisKelas>();
        for (JenisKelas jenisKelas : jenisKelasDb.findAll()) {
            ReadJenisKelas response = JenisKelasMapper.toReadDto(jenisKelas);
            listJenisKelas.add(response);
        }
        return listJenisKelas;
    }

    @Override
    public JenisKelas save(CreateJenisKelas jenisKelasRequest) {

        if (!getByNama(jenisKelasRequest.getNama()).isEmpty()) {
            throw new BadRequestException("Tag with name " + jenisKelasRequest.getNama() + " already exists");
        }

        UserModel picAkademik = userService.getUserById(jenisKelasRequest.getPicAkademikId());

        return jenisKelasDb.save(JenisKelasMapper.toEntity(jenisKelasRequest, picAkademik));
    }

    @Override
    public Optional<JenisKelas> getById(UUID id) {
        return jenisKelasDb.findById(id);
    }

    @Override
    public void delete(UUID id) {
        if (!getById(id).isPresent())
            throw new BadRequestException("Jenis Kelas with id " + id + " not found");

        jenisKelasDb.deleteById(id);
    }

    @Override
    public Optional<JenisKelas> getByNama(String nama) {
        return jenisKelasDb.findByNama(nama);
    }

    @Override
    public JenisKelas getByNamaAndPertemuanAndTipeAndBahasa(String nama, String pertemuan, String tipe, String bahasa) {
        return jenisKelasDb.findByNamaAndPertemuanAndTipeAndBahasa(nama, pertemuan, tipe, bahasa);
    }

    @Override
    public ReadJenisKelas update(UpdateJenisKelas jenisKelasRequest) {
        JenisKelas existingJenisKelas = jenisKelasDb.findById(jenisKelasRequest.getId()).orElse(null);

        if (existingJenisKelas == null)
            throw new BadRequestException("Jenis Kelas with id " + jenisKelasRequest.getId() + " not found");

        existingJenisKelas.setNama(jenisKelasRequest.getNama());
        existingJenisKelas.setPertemuan(jenisKelasRequest.getPertemuan());
        existingJenisKelas.setTipe(jenisKelasRequest.getTipe());
        existingJenisKelas.setBahasa(jenisKelasRequest.getBahasa());

        return JenisKelasMapper.toReadDto(jenisKelasDb.save(existingJenisKelas));
    }
}
