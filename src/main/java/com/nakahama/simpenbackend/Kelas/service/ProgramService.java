package com.nakahama.simpenbackend.Kelas.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.model.Program;
import com.nakahama.simpenbackend.Kelas.repository.ProgramDb;

@Service
public class ProgramService {
    
    @Autowired
    ProgramDb programDb;

    public List<Program> getAll() {
        return programDb.findAll();
    }

    public Program save(Program program) {
        return programDb.save(program);
    }

    public Optional<Program> getById(UUID id) {
        return programDb.findById(id);
    }

    public void delete(UUID id) {
        programDb.deleteById(id);
    }

    public Optional<Program> getByNama(String nama) {
        return programDb.findByNama(nama);
    }
}
