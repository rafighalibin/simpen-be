package com.nakahama.simpenbackend.Kelas.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.JenisKelasDTO;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.JenisKelasMapper;
import com.nakahama.simpenbackend.Kelas.dto.Program.CreateProgram;
import com.nakahama.simpenbackend.Kelas.dto.Program.UpdateProgram;
import com.nakahama.simpenbackend.Kelas.dto.Program.ProgramMapper;
import com.nakahama.simpenbackend.Kelas.dto.Program.ReadDistinctJenisKelasProgram;
import com.nakahama.simpenbackend.Kelas.dto.Program.ReadProgram;
import com.nakahama.simpenbackend.Kelas.model.JenisKelas;
import com.nakahama.simpenbackend.Kelas.model.Program;
import com.nakahama.simpenbackend.Kelas.repository.ProgramDb;
import com.nakahama.simpenbackend.Kelas.repository.JenisKelas.JenisKelasDb;
import com.nakahama.simpenbackend.exception.BadRequestException;

@Service
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    ProgramDb programDb;

    @Autowired
    JenisKelasService jenisKelasService;

    @Autowired
    JenisKelasDb jenisKelasDb;

    @Override
    public List<ReadProgram> getAll() {
        List<ReadProgram> listProgram = new ArrayList<ReadProgram>();
        for (Program program : programDb.findAll()) {
            ReadProgram response = ProgramMapper.toReadDto(program);
            response.setListJenisKelas(new ArrayList<JenisKelasDTO>());
            for (JenisKelas jenisKelas : program.getJenisKelas()) {
                response.getListJenisKelas().add(JenisKelasMapper.toDto(jenisKelas));
            }
            listProgram.add(response);
        }
        return listProgram;
    }

    @Override
    public Program save(CreateProgram programRequest) {
        Program program = ProgramMapper.toEntity(programRequest);
        program.setJenisKelas(new ArrayList<JenisKelas>());
        programDb.save(program);
        for (UUID jenisKelasId : programRequest.getJenisKelas()) {
            JenisKelas jenisKelas = jenisKelasService.getById(jenisKelasId);
            if (!jenisKelas.getProgram().contains(program)) {
                jenisKelas.getProgram().add(program);
            }
            program.getJenisKelas().add(jenisKelas);
        }

        programDb.save(program);

        return program;
    }

    @Override
    public Program getById(UUID id) {
        Optional<Program> program = programDb.findById(id);
        if (!program.isPresent()) {
            throw new BadRequestException("Program with id " + id + " not found");
        }
        return program.get();
    }

    @Override
    public void delete(UUID id) {
        Program programRequest = getById(id);
        programRequest.getJenisKelas().forEach(jenisKelas -> {
            jenisKelas.getProgram().remove(programRequest);
            jenisKelasDb.save(jenisKelas);
        });

        // Save all changes to jenisKelasDb before proceeding
        jenisKelasDb.flush();
        
        programDb.deleteById(programRequest.getId());
    }

    @Override
    public ReadProgram getProgramById(UUID id) {
        Program program = getById(id);
        ReadProgram response = ProgramMapper.toReadDto(program);
        response.setListJenisKelas(new ArrayList<JenisKelasDTO>());
        for (JenisKelas jenisKelas : program.getJenisKelas()) {
            response.getListJenisKelas().add(JenisKelasMapper.toDto(jenisKelas));
        }
        return response;
    }

    @Override
    public Optional<Program> getByNama(String nama) {
        return programDb.findByNama(nama);
    }

    @Override
    public ReadProgram update(UpdateProgram programRequest) {
        Program existingProgram = programDb.findById(programRequest.getId()).orElse(null);
        existingProgram.setNama(programRequest.getNama());
        existingProgram.setJumlahLevel(programRequest.getJumlahLevel());
        existingProgram.setJumlahPertemuan(programRequest.getJumlahPertemuan());
        existingProgram = programDb.save(existingProgram);

        ReadProgram response = ProgramMapper.toReadDto(existingProgram);
        response.setListJenisKelas(new ArrayList<JenisKelasDTO>());
        for (JenisKelas jenisKelas : existingProgram.getJenisKelas()) {
            response.getListJenisKelas().add(JenisKelasMapper.toDto(jenisKelas));
        }
        return response;
    }

    @Override
    public List<ReadDistinctJenisKelasProgram> getDistinctJenisKelas(UUID id) {
        List<String> jenisKelas = programDb.findDistinctJenisKelasByProgramId(id);

        if (jenisKelas.isEmpty()) {
            throw new BadRequestException("Program with id " + id + " not found");
        }

        List<ReadDistinctJenisKelasProgram> listJenisKelas = new ArrayList<ReadDistinctJenisKelasProgram>();

        for (String jenisKelasValue : jenisKelas) {
            ReadDistinctJenisKelasProgram response = new ReadDistinctJenisKelasProgram();
            response.setNama(jenisKelasValue);
            listJenisKelas.add(response);
        }

        return listJenisKelas;
    }
}
