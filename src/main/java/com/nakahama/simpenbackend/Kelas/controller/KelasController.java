package com.nakahama.simpenbackend.Kelas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

import com.nakahama.simpenbackend.Kelas.model.*;
import com.nakahama.simpenbackend.User.model.UserModel;
import com.nakahama.simpenbackend.Kelas.dto.CreateKelasRequestDTO;
import com.nakahama.simpenbackend.Kelas.service.*;
import com.nakahama.simpenbackend.util.BaseResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController("/kelas")
public class KelasController {
    
    @Autowired
    KelasServiceImpl kelasService;

    @Autowired
    ProgramServiceImpl programService;

    @Autowired
    JenisKelasService jenisKelasService;

    @Autowired
    SesiKelasService sesiKelasService;

    @Autowired
    MuridService muridService;

    @Autowired
    MuridKelasService muridKelasService;

    @PostMapping("/")
    public BaseResponse createKelas(@RequestBody CreateKelasRequestDTO kelasDTO) {

        Optional<Program> program = programService.getById(kelasDTO.getProgramId());

        if (program.isEmpty()) {
            BaseResponse response = new BaseResponse();
            response.setCode(404);
            response.setStatus("Not Found");
            response.setMessage("Program not found");
            return response;
        }

        Optional<JenisKelas> jenisKelas = jenisKelasService.getById(kelasDTO.getJenisKelasId());

        if (jenisKelas.isEmpty()) {
            BaseResponse response = new BaseResponse();
            response.setCode(404);
            response.setStatus("Not Found");
            response.setMessage("Jenis Kelas not found");
            return response;
        }

        Kelas kelas = new Kelas();
        kelas.setProgram(program.get());
        kelas.setJenisKelas(jenisKelas.get());
        UserModel operasional = new UserModel(); // need to change after service to get user by jwt is created
        kelas.setOperasional(operasional);
        UserModel pengajar = new UserModel(); // need to change after Pengajar model is created
        kelas.setPengajar(pengajar);
        kelas.setLevel(kelasDTO.getLevel());
        kelas.setTanggalMulai(kelasDTO.getTanggalMulai());
        kelas.setTanggalSelesai(kelasDTO.getTanggalSelesai());

        if(kelasDTO.getLinkGroup() != null) {
            kelas.setLinkGroup(kelasDTO.getLinkGroup());
        }

        Kelas createdKelas = kelasService.save(kelas);

        for(UUID e : kelasDTO.getListMurid()){
            Optional<Murid> murid = muridService.getById(e);
            if(murid.isPresent()){
                MuridKelas muridKelas = new MuridKelas();
                muridKelas.setKelas(kelas);
                muridKelas.setMurid(murid.get());
                muridKelasService.save(muridKelas);
            }
        }

        kelas.setJumlahMurid(kelasDTO.getListMurid().size());

        for(LocalDateTime e : kelasDTO.getJadwalKelas()){
            SesiKelas sesiKelas = new SesiKelas();
            sesiKelas.setKelas(createdKelas);
            sesiKelas.setPengajar(pengajar);
            sesiKelas.setPlatform(kelasDTO.getPlatform());  //need to change after platform model is created (Sprint 2)
            sesiKelas.setWaktuPelaksanaan(e);
            sesiKelas.setStatus("Scheduled");
            sesiKelasService.save(sesiKelas);
        }

        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setStatus("OK");
        response.setMessage("Success");
        response.setContent(createdKelas);
        return response;
    }
}
