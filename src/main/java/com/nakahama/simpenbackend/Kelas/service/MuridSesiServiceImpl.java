package com.nakahama.simpenbackend.Kelas.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.model.MuridKelas;
import com.nakahama.simpenbackend.Kelas.model.MuridSesi;
import com.nakahama.simpenbackend.Kelas.model.SesiKelas;
import com.nakahama.simpenbackend.Kelas.repository.MuridSesiDb;

@Service
public class MuridSesiServiceImpl implements MuridSesiService {

    @Autowired
    MuridSesiDb muridSesiDb;

    @Override
    public List<MuridSesi> getAll() {
        return muridSesiDb.findAll();
    }

    @Override
    public MuridSesi save(MuridSesi muridSesi) {
        return muridSesiDb.save(muridSesi);
    }

    @Override
    public MuridSesi getById(UUID id) {
        MuridSesi muridSesi = muridSesiDb.findById(id).get();
        if (muridSesi == null)
            throw new NoSuchElementException("MuridSesi with id " + id + " not found");
        return muridSesi;
    }

    @Override
    public void delete(UUID id) {
        muridSesiDb.deleteById(id);
    }

    @Override
    public List<MuridSesi> createListMuridSesi(List<MuridKelas> listMurid, SesiKelas sesiKelas) {

        List<MuridSesi> listMuridSesi = new ArrayList<>();
        for (MuridKelas muridKelas : listMurid) {
            MuridSesi muridSesi = new MuridSesi();
            muridSesi.setMurid(muridKelas.getMurid());
            muridSesi.setSesiKelas(sesiKelas);
            save(muridSesi);
            listMuridSesi.add(muridSesi);
        }
        return listMuridSesi;
    }

}
