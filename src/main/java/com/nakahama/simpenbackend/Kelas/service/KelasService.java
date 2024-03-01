package com.nakahama.simpenbackend.Kelas.service;

import java.util.List;
import java.util.Optional;
import com.nakahama.simpenbackend.Kelas.model.Kelas;

public interface KelasService {
    
    public List<Kelas> getAll();

    public Kelas save(Kelas kelas);

    public Optional<Kelas> getById(int id);

    public void delete(int id);
}
