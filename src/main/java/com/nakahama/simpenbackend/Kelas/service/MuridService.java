package com.nakahama.simpenbackend.Kelas.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.nakahama.simpenbackend.Kelas.model.Murid;

public interface MuridService {
    
        public List<Murid> getAll();
    
        public Murid save(Murid murid);
    
        public Optional<Murid> getById(UUID id);
    
        public void delete(UUID id);
    
        public Optional<Murid> getByNama(String nama);
    
        public Optional<Murid> getByNamaOrtu(String namaOrtu);
    
        public Optional<Murid> getByEmailOrtu(String emailOrtu);
    
        public Optional<Murid> getByNoHpOrtu(String noHpOrtu);

}
