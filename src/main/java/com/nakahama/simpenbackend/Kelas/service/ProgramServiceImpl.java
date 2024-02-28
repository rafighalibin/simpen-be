package com.nakahama.simpenbackend.Kelas.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.model.Program;
import com.nakahama.simpenbackend.Kelas.repository.ProgramDb;

@Service
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    ProgramDb programDb;

    @Override
    public List<Program> getAll() {
        return programDb.findAll();
    }

    @Override
    public Program save(Program program) {
        return programDb.save(program);
    }

    @Override
    public Optional<Program> getById(UUID id) {
        return programDb.findById(id);
    }

    @Override
    public void delete(UUID id) {
        programDb.deleteById(id);
    }

    @Override
    public Optional<Program> getByNama(String nama) {
        return programDb.findByNama(nama);
    }

    @Override
    public Program update(Program programRequest) {
        Program existingProgram = programDb.findById(programRequest.getId()).orElse(null);
        existingProgram.setNama(programRequest.getNama());
        existingProgram.setJumlahLevel(programRequest.getJumlahLevel());
        existingProgram.setJumlahPertemuan(programRequest.getJumlahPertemuan());

        return programDb.save(existingProgram);
    }
}
