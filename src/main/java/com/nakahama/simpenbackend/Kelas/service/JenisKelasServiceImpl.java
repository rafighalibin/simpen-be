package com.nakahama.simpenbackend.Kelas.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.CreateJenisKelas;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.JenisKelasMapper;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.ReadJenisKelas;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.UpdateJenisKelas;
import com.nakahama.simpenbackend.Kelas.model.*;
import com.nakahama.simpenbackend.Kelas.repository.JenisKelas.BahasaDb;
import com.nakahama.simpenbackend.Kelas.repository.JenisKelas.JenisKelasDb;
import com.nakahama.simpenbackend.Kelas.repository.JenisKelas.ModaPertemuanDb;
import com.nakahama.simpenbackend.Kelas.repository.JenisKelas.TipeDb;
import com.nakahama.simpenbackend.User.model.UserModel;
import com.nakahama.simpenbackend.User.service.UserService;
import com.nakahama.simpenbackend.exception.BadRequestException;

@Service
public class JenisKelasServiceImpl implements JenisKelasService {

    @Autowired
    JenisKelasDb jenisKelasDb;

    @Autowired
    UserService userService;

    @Autowired
    ModaPertemuanDb modaPertemuanDb;

    @Autowired
    TipeDb tipeDb;

    @Autowired
    BahasaDb bahasaDb;

    @Override
    public List<ReadJenisKelas> getAll() {
        List<ReadJenisKelas> listJenisKelas = new ArrayList<ReadJenisKelas>();
        for (JenisKelas jenisKelas : jenisKelasDb.findAll()) {
            ReadJenisKelas response = JenisKelasMapper.toReadDto(jenisKelas);
            listJenisKelas.add(response);
        }
        return listJenisKelas;
    }

    @Override
    public void save(CreateJenisKelas jenisKelasRequest) {

        if (!getByNama(jenisKelasRequest.getNama()).isEmpty()) {
            throw new BadRequestException("Tag with name " + jenisKelasRequest.getNama() + " already exists");
        }

        UserModel picAkademik = userService.getUserById(jenisKelasRequest.getPicAkademikId());
        List<JenisKelas> listJenisKelas = JenisKelasMapper.toEntity(jenisKelasRequest, picAkademik);

        saveJenisAttr(jenisKelasRequest.getModaPertemuan(), jenisKelasRequest.getTipe(), jenisKelasRequest.getBahasa());
        for (JenisKelas jenisKelas : listJenisKelas) {
            jenisKelasDb.save(jenisKelas);
        }

    }

    private void saveJenisAttr(List<String> listModaPertemuan, List<String> ListTipe, List<String> listBahasa) {
        for (String modaPertemuanValue : listModaPertemuan) {
            if (modaPertemuanDb.findById(modaPertemuanValue).isEmpty()) {
                ModaPertemuan modaPertemuanEntity = new ModaPertemuan();
                modaPertemuanEntity.setNama(modaPertemuanValue);
                modaPertemuanDb.save(modaPertemuanEntity);
            }
        }

        for (String tipeValue : ListTipe) {
            if (tipeDb.findById(tipeValue).isEmpty()) {
                Tipe tipeEntity = new Tipe();
                tipeEntity.setNama(tipeValue);
                tipeDb.save(tipeEntity);
            }
        }

        for (String bahasa : listBahasa) {
            if (bahasaDb.findById(bahasa).isEmpty()) {
                Bahasa bahasaEntity = new Bahasa();
                bahasaEntity.setNama(bahasa);
                bahasaDb.save(bahasaEntity);
            }
        }
    }

    @Override
    public Optional<JenisKelas> getById(UUID id) {
        return jenisKelasDb.findById(id);
    }

    @Override
    public void delete(UUID id) {
        if (!getById(id).isPresent())
            throw new BadRequestException("Jenis Kelas with id " + id + " not found");

        jenisKelasDb.deleteById(id);
    }

    @Override
    public List<JenisKelas> getByNama(String nama) {
        return jenisKelasDb.findAllByNama(nama);
    }

    @Override
    public ReadJenisKelas update(UpdateJenisKelas jenisKelasRequest) {
        List<JenisKelas> existingJenisKelas = jenisKelasDb.findAllByNama(jenisKelasRequest.getNama());

        if (existingJenisKelas == null)
            throw new BadRequestException("Jenis Kelas with nama " + jenisKelasRequest.getNama() + " not found");

        UserModel picAkademik = userService.getUserById(jenisKelasRequest.getPicAkademikId());
        List<JenisKelas> listJenisKelas = JenisKelasMapper.toEntity(jenisKelasRequest, picAkademik);

        List<JenisKelas> addedJenisKelas = new ArrayList<JenisKelas>();
        for (JenisKelas jenisKelas : listJenisKelas) {
            try {
                jenisKelasDb.save(jenisKelas);
                addedJenisKelas.add(jenisKelas);
            } catch (Exception e) {
                continue;
            }
        }
        saveJenisAttr(jenisKelasRequest.getModaPertemuan(), jenisKelasRequest.getTipe(), jenisKelasRequest.getBahasa());

        if (addedJenisKelas.isEmpty()) {
            throw new BadRequestException("Jenis Kelas with name " + jenisKelasRequest.getNama() + " does not change");
        }

        return JenisKelasMapper.toReadDto(addedJenisKelas.get(0));
    }

    @Override
    public Map<String, List<String>> getExistingAttributes() {
        Map<String, List<String>> existingAttributes = new HashMap<String, List<String>>();
        List<ModaPertemuan> modaPertemuan = modaPertemuanDb.findAll();
        List<Tipe> tipe = tipeDb.findAll();
        List<Bahasa> bahasa = bahasaDb.findAll();

        List<String> modaPertemuanList = new ArrayList<String>();
        List<String> tipeList = new ArrayList<String>();
        List<String> bahasaList = new ArrayList<String>();

        for (ModaPertemuan modaPertemuanValue : modaPertemuan) {
            modaPertemuanList.add(modaPertemuanValue.getNama());
        }

        for (Tipe tipeValue : tipe) {
            tipeList.add(tipeValue.getNama());
        }

        for (Bahasa bahasaValue : bahasa) {
            bahasaList.add(bahasaValue.getNama());
        }

        existingAttributes.put("modaPertemuan", modaPertemuanList);
        existingAttributes.put("tipe", tipeList);
        existingAttributes.put("bahasa", bahasaList);

        return existingAttributes;
    }
}
