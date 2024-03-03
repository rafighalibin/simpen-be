package com.nakahama.simpenbackend.Kelas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import com.nakahama.simpenbackend.Kelas.model.*;
import com.nakahama.simpenbackend.User.service.UserService;
import com.nakahama.simpenbackend.Auth.service.AuthService;
import com.nakahama.simpenbackend.Kelas.dto.Kelas.CreateKelasRequestDTO;
import com.nakahama.simpenbackend.Kelas.dto.Kelas.KelasMapper;
import com.nakahama.simpenbackend.Kelas.dto.Kelas.ReadKelas;
import com.nakahama.simpenbackend.Kelas.service.*;
import com.nakahama.simpenbackend.util.ResponseUtil;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
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

    @GetMapping("/kelas")
    public ResponseEntity<Object> getKelas() {

        List<ReadKelas> listKelas = new ArrayList<ReadKelas>();
        for (Kelas kelas : kelasService.getAll()) {
            listKelas.add(KelasMapper.toReadDto(kelas));
        }
        return ResponseUtil.okResponse(listKelas, "Success");
    }

    @SuppressWarnings("deprecation")
    @PostMapping("/kelas")
    public ResponseEntity<Object> createKelas(@RequestBody CreateKelasRequestDTO createKelasRequestDTO,
            HttpServletRequest request) {
        createKelasRequestDTO.setOperasional(authService.getLoggedUser(request));
        Kelas createdKelas = kelasService.save(createKelasRequestDTO);
        return ResponseUtil.okResponse(KelasMapper.toDetailDto(createdKelas, createdKelas.getListsesiKelas()),
                "Kelas dengan id " + createdKelas.getKelasId() + " berhasil dibuat");
    }
}
