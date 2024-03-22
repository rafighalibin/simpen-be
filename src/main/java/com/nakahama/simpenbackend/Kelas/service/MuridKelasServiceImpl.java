package com.nakahama.simpenbackend.Kelas.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.model.Kelas;
import com.nakahama.simpenbackend.Kelas.model.Murid;
import com.nakahama.simpenbackend.Kelas.model.MuridKelas;
import com.nakahama.simpenbackend.Kelas.repository.MuridKelasDb;

@Service
public class MuridKelasServiceImpl implements MuridKelasService {

    @Autowired
    MuridKelasDb muridKelasDb;

    @Autowired
    MuridService muridService;

    @Override
    public List<MuridKelas> getAll() {
        return muridKelasDb.findAll();
    }

    @Override
    public MuridKelas save(MuridKelas muridKelas) {
        return muridKelasDb.save(muridKelas);
    }

    @Override
    public MuridKelas getById(UUID id) {
        MuridKelas muridKelas = muridKelasDb.findById(id).get();
        if (muridKelas == null)
            throw new NoSuchElementException("Murid Kelas with id " + id + " not found");
        return muridKelas;
    }

    @Override
    public void delete(UUID id) {
        muridKelasDb.deleteById(id);
    }

    @Override
    public List<MuridKelas> getByMuridAndKelas(Murid murid, Kelas kelas) {
        List<MuridKelas> muridKelas = muridKelasDb.findAllByMuridAndKelas(murid, kelas);
        if (muridKelas == null)
            throw new NoSuchElementException("Murid Kelas with murid " + murid + " not found");
        return muridKelas;
    }

    @Override
    public Optional<MuridKelas> getByKelas(Kelas kelas) {
        return muridKelasDb.findByKelas(kelas);
    }

    @Override
    public Optional<MuridKelas> getByLinkReport(String linkReport) {
        return muridKelasDb.findByLinkReport(linkReport);
    }

    @Override
    public List<MuridKelas> getListMurid(List<Integer> listMurid, Kelas kelas) {
        List<MuridKelas> muridKelas = new ArrayList<>();
        for (int idMurid : listMurid) {
            List<MuridKelas> muridKelasTemp = getByMuridAndKelas(muridService.getById(idMurid), kelas);
            muridKelas.addAll(muridKelasTemp);
        }
        return muridKelas;
    }

    @Override
    public List<MuridKelas> createListMuridKelas(List<Integer> listMurid, Kelas kelas) {
        List<MuridKelas> muridKelas = new ArrayList<>();
        for (int idMurid : listMurid) {
            MuridKelas muridKelasTemp = new MuridKelas();
            muridKelasTemp.setMurid(muridService.getById(idMurid));
            muridKelasTemp.setKelas(kelas);
            save(muridKelasTemp);
            muridKelas.add(muridKelasTemp);
        }
        return muridKelas;
    }

}
