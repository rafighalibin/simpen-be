package com.nakahama.simpenbackend.Kelas.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.dto.Program.CreateProgram;
import com.nakahama.simpenbackend.Kelas.dto.Program.ReadDistinctJenisKelasProgram;
import com.nakahama.simpenbackend.Kelas.dto.Program.ReadProgram;
import com.nakahama.simpenbackend.Kelas.dto.Program.UpdateProgram;
import com.nakahama.simpenbackend.Kelas.model.Program;

@Service
public interface ProgramService {

    public List<ReadProgram> getAll();

    public Program save(CreateProgram programRequest);

    public Program getById(UUID id);

    public void delete(UUID id);

    public Optional<Program> getByNama(String nama);

    public ReadProgram update(UpdateProgram programRequest);

    public ReadProgram getProgramById(UUID id);

    public List<ReadDistinctJenisKelasProgram> getDistinctJenisKelas(UUID id);
}
