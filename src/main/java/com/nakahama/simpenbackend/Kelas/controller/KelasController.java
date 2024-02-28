package com.nakahama.simpenbackend.Kelas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    ProgramService programService;

    @Autowired
    JenisKelasService jenisKelasService;

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
        UserModel operasional = new UserModel(); // need to change after Operasional model is created
        kelas.setOperasional(operasional);
        UserModel pengajar = new UserModel(); // need to change after Pengajar model is created
        kelas.setPengajar(pengajar);
        kelas.setLevel(kelasDTO.getLevel());
        kelas.setTanggalMulai(kelasDTO.getTanggalMulai());
        kelas.setTanggalSelesai(kelasDTO.getTanggalSelesai());

        if(kelasDTO.getLinkGroup() != null) {
            kelas.setLinkGroup(kelasDTO.getLinkGroup());
        }

        if(kelasDTO.getListMurid() != null) {
            kelas.setJumlahMurid(kelasDTO.getListMurid().size());
        }

        Kelas createdKelas = kelasService.save(kelas);

        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setStatus("OK");
        response.setMessage("Success");
        response.setContent(createdKelas);
        return response;
    }
}
