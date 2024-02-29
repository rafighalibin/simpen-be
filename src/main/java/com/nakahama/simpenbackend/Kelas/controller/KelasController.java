package com.nakahama.simpenbackend.Kelas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

import com.nakahama.simpenbackend.Kelas.model.*;
import com.nakahama.simpenbackend.User.model.UserModel;
import com.nakahama.simpenbackend.User.service.UserService;
import com.nakahama.simpenbackend.Auth.service.AuthService;
import com.nakahama.simpenbackend.Kelas.dto.CreateKelasRequestDTO;
import com.nakahama.simpenbackend.Kelas.service.*;
import com.nakahama.simpenbackend.util.BaseResponse;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController("/kelas")
public class KelasController {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;
    
    @Autowired
    KelasServiceImpl kelasService;

    @Autowired
    ProgramServiceImpl programService;

    @Autowired
    JenisKelasService jenisKelasService;

    @Autowired
    SesiKelasService sesiKelasService;

    @GetMapping("/")
    public BaseResponse getKelas() {
        List<Kelas> listKelas = kelasService.getAll();

        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setStatus("OK");
        response.setMessage("Success");
        response.setContent(listKelas);
        return response;
    }

    @PostMapping("/")
    public BaseResponse createKelas(@RequestBody CreateKelasRequestDTO kelasDTO, HttpServletRequest request) {

        @SuppressWarnings("deprecation")
        UserModel userLoggedIn = authService.getLoggedUser(request);

        if (userLoggedIn == null) {
            BaseResponse response = new BaseResponse();
            response.setCode(401);
            response.setStatus("Unauthorized");
            response.setMessage("User not found");
            return response;
        }

        if(!userLoggedIn.getRole().equals("operasional")) {
            BaseResponse response = new BaseResponse();
            response.setCode(403);
            response.setStatus("Forbidden");
            response.setMessage("User not authorized");
            return response;
        }

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
        kelas.setKelasId(kelasService.getAll().size() + 1);
        kelas.setProgram(program.get());
        kelas.setJenisKelas(jenisKelas.get());
        kelas.setOperasional(userLoggedIn);
        UserModel pengajar = userService.getUserById(kelasDTO.getPengajarId());
        kelas.setPengajar(pengajar);
        kelas.setLevel(kelasDTO.getLevel());
        kelas.setTanggalMulai(kelasDTO.getTanggalMulai());
        kelas.setTanggalSelesai(kelasDTO.getTanggalSelesai());
        kelas.setJumlahMurid(kelasDTO.getListMurid().size());

        if(kelasDTO.getLinkGroup() != null) {
            kelas.setLinkGroup(kelasDTO.getLinkGroup());
        }

        kelas.setListMurid(kelasDTO.getListMurid());

        Kelas createdKelas = kelasService.save(kelas);

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
