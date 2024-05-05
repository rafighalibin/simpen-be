package com.nakahama.simpenbackend.Kelas.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.CreateJenisKelas;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.FindJenisKelas;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.JenisKelasMapper;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.ProgramJenisKelasAttributes;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.ReadJenisKelas;
import com.nakahama.simpenbackend.Kelas.dto.Program.ProgramDTO;
import com.nakahama.simpenbackend.Kelas.dto.Program.ProgramMapper;
import com.nakahama.simpenbackend.Kelas.model.*;
import com.nakahama.simpenbackend.Kelas.repository.ProgramDb;
import com.nakahama.simpenbackend.Kelas.repository.JenisKelas.BahasaDb;
import com.nakahama.simpenbackend.Kelas.repository.JenisKelas.JenisKelasDb;
import com.nakahama.simpenbackend.Kelas.repository.JenisKelas.ModaPertemuanDb;
import com.nakahama.simpenbackend.Kelas.repository.JenisKelas.TipeDb;
import com.nakahama.simpenbackend.User.model.Akademik;
import com.nakahama.simpenbackend.User.model.UserModel;
import com.nakahama.simpenbackend.User.service.UserService;
import com.nakahama.simpenbackend.exception.BadRequestException;
import com.nakahama.simpenbackend.Payroll.model.FeeModel;
import com.nakahama.simpenbackend.Payroll.repository.FeeDb;

@Service
public class JenisKelasServiceImpl implements JenisKelasService {

    @Autowired
    ProgramDb  programDb;

    @Autowired
    JenisKelasDb jenisKelasDb;

    @Autowired
    UserService userService;

    @Autowired
    FeeDb feeDb;

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
            response.setListProgram(new ArrayList<ProgramDTO>());
            for (Program program : jenisKelas.getProgram()) {
                response.getListProgram().add(ProgramMapper.toDto(program));
            }
            listJenisKelas.add(response);
        }
        return listJenisKelas;
    }

    @Override
    public ReadJenisKelas getJenisKelasById(UUID id) {
        JenisKelas jenisKelas = getById(id);
        ReadJenisKelas response = JenisKelasMapper.toReadDto(jenisKelas);
        response.setListProgram(new ArrayList<ProgramDTO>());
        for (Program program : jenisKelas.getProgram()) {
            response.getListProgram().add(ProgramMapper.toDto(program));
        }
        return response;
    }

    @Override
    public void save(CreateJenisKelas jenisKelasRequest) {

        if (!getByNama(jenisKelasRequest.getNama()).isEmpty()) {
            throw new BadRequestException("Jenis with name " + jenisKelasRequest.getNama() + " already exists");
        }

        UserModel picAkademikUserModel = userService.getUserById(jenisKelasRequest.getPicAkademikId());
        Akademik picAkademik = (Akademik) picAkademikUserModel;
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
    public JenisKelas getById(UUID id) {

        Optional<JenisKelas> jenisKelas = jenisKelasDb.findById(id);
        if (jenisKelas.isEmpty()) {
            throw new BadRequestException("Jenis Kelas with id " + id + " not found");
        }

        return jenisKelas.get();
    }

    @Override
    public void delete(UUID id) {
        JenisKelas jenisKelas = getById(id);
        jenisKelasDb.deleteById(id);

        if (jenisKelasDb.findByTipe(jenisKelas.getTipe()).isEmpty()) {
            tipeDb.deleteById(jenisKelas.getTipe());
        }
        if (jenisKelasDb.findByModaPertemuan(jenisKelas.getModaPertemuan()).isEmpty()) {
            modaPertemuanDb.deleteById(jenisKelas.getModaPertemuan());
        }
        if (jenisKelasDb.findByBahasa(jenisKelas.getBahasa()).isEmpty()) {
            bahasaDb.deleteById(jenisKelas.getBahasa());
        }
    }

    @Override
    public List<JenisKelas> getByNama(String nama) {
        return jenisKelasDb.findAllByNama(nama);
    }

    @Override
    public ReadJenisKelas createToUpdate(CreateJenisKelas jenisKelasRequest) {
        UserModel picAkademikUserModel = userService.getUserById(jenisKelasRequest.getPicAkademikId());
        Akademik picAkademik = (Akademik) picAkademikUserModel;
        List<JenisKelas> listJenisKelas = JenisKelasMapper.toEntity(jenisKelasRequest, picAkademik);

        List<JenisKelas> addedJenisKelas = new ArrayList<JenisKelas>();
        for (JenisKelas jenisKelas : listJenisKelas) {
            // Check if the combination already exists
            if (jenisKelasDb.findByNamaAndTipeAndModaPertemuanAndBahasa(jenisKelas.getNama(), jenisKelas.getTipe(), jenisKelas.getModaPertemuan(), jenisKelas.getBahasa()).isEmpty()) {
                try {
                    jenisKelasDb.save(jenisKelas);
                    addedJenisKelas.add(jenisKelas);
                } catch (Exception e) {
                    continue;
                }
            }
        }
        saveJenisAttr(jenisKelasRequest.getModaPertemuan(), jenisKelasRequest.getTipe(), jenisKelasRequest.getBahasa());

        if (addedJenisKelas.isEmpty()) {
            throw new BadRequestException("Jenis Kelas with name " + jenisKelasRequest.getNama() + " does not change");
        }

        return JenisKelasMapper.toReadDto(addedJenisKelas.get(0));
    }

    @Override
    public Map<String, List<String>> getAllExistingAttributes() {
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

    @Override
    public Map<String, List<String>> getExistingAttributes(ProgramJenisKelasAttributes programJenisKelasAttributes){

        Map<String, List<String>> existingAttributes = new HashMap<String, List<String>>();

        Program program = programDb.findById(programJenisKelasAttributes.getProgramId()).get();

        List<String> modaPertemuanList = new ArrayList<String>();
        List<String> tipeList = new ArrayList<String>();
        List<String> bahasaList = new ArrayList<String>();

        for (JenisKelas jk : jenisKelasDb.findAllByNama(programJenisKelasAttributes.getNamaJenisKelas())) {
            if (jk.getProgram().contains(program)) {
                if(!modaPertemuanList.contains(jk.getModaPertemuan()))
                    modaPertemuanList.add(jk.getModaPertemuan());
                if(!tipeList.contains(jk.getTipe()))
                    tipeList.add(jk.getTipe());
                if(!bahasaList.contains(jk.getBahasa()))
                    bahasaList.add(jk.getBahasa());
            }
        }
        
        existingAttributes.put("modaPertemuan", modaPertemuanList);
        existingAttributes.put("tipe", tipeList);
        existingAttributes.put("bahasa", bahasaList);

        return existingAttributes;
    }

    @Override
    public ReadJenisKelas findJenisKelas(String nama, String tipe, String moda, String bahasa) {
        List<JenisKelas> jenisKelas = jenisKelasDb.findAllByNama(nama);
        for (JenisKelas jk : jenisKelas) {
            if (jk.getTipe().equals(tipe) && jk.getModaPertemuan().equals(moda) && jk.getBahasa().equals(bahasa)) {
                return JenisKelasMapper.toReadDto(jk);
            }
        }
        throw new BadRequestException("Jenis Kelas not found");
    }

    @Override
    public ReadJenisKelas findJenisKelasRequest(FindJenisKelas findJenisKelasRequest) {
        List<JenisKelas> jenisKelas = jenisKelasDb.findAllByNama(findJenisKelasRequest.getNama());
        for (JenisKelas jk : jenisKelas) {
            if (jk.getTipe().equals(findJenisKelasRequest.getTipe()) && jk.getModaPertemuan().equals(findJenisKelasRequest.getModaPertemuan()) && jk.getBahasa().equals(findJenisKelasRequest.getBahasa())) {
                return JenisKelasMapper.toReadDto(jk);
            }
        }
        throw new BadRequestException("Jenis Kelas not found");
    }

    @Override
    public Boolean hasFee(UUID jenisKelasId, UUID programId) {
        JenisKelas jenisKelas = getById(jenisKelasId);
        Optional<Program> program = programDb.findById(programId);
        FeeModel fee = feeDb.findByProgramAndJenisKelas(program.get(), jenisKelas);

        if (fee != null) {
            return true;
        }
        
        return false;
    }
}
