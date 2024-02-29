package com.nakahama.simpenbackend.Kelas.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.model.Program;

@Service
public interface ProgramService {

    public List<Program> getAll();

    public Program save(Program program);

    public Optional<Program> getById(UUID id);

    public void delete(UUID id);

    public Optional<Program> getByNama(String nama);

    public Program update(Program programRequest);
}
