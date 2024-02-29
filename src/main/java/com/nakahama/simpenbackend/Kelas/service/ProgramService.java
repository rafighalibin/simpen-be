package com.nakahama.simpenbackend.Kelas.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.nakahama.simpenbackend.Kelas.model.Program;

public interface ProgramService {
        
        public List<Program> getAll();
    
        public Program save(Program program);
    
        public Optional<Program> getById(UUID id);
    
        public void delete(UUID id);
    
        public Optional<Program> getByNama(String nama);
}
