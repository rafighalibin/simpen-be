package com.nakahama.simpenbackend.Kelas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import com.nakahama.simpenbackend.Kelas.model.*;
import com.nakahama.simpenbackend.User.service.UserService;
import com.nakahama.simpenbackend.Auth.service.AuthService;
import com.nakahama.simpenbackend.Kelas.dto.Kelas.CreateKelas;
import com.nakahama.simpenbackend.Kelas.dto.Kelas.KelasMapper;
import com.nakahama.simpenbackend.Kelas.dto.Kelas.ReadKelas;
import com.nakahama.simpenbackend.Kelas.dto.Kelas.UpdateKelas;
import com.nakahama.simpenbackend.Kelas.service.*;
import com.nakahama.simpenbackend.util.ResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    public ResponseEntity<Object> createKelas(@Valid @RequestBody CreateKelas createKelasRequest,
            HttpServletRequest request) {
        createKelasRequest.setOperasional(authService.getLoggedUser(request));
        Kelas createdKelas = kelasService.save(createKelasRequest);
        return ResponseUtil.okResponse(KelasMapper.toDetailDto(createdKelas, createdKelas.getListsesiKelas()),
                "Kelas dengan id " + createdKelas.getKelasId() + " berhasil dibuat");
    }

    @GetMapping("/kelas/{kelasId}")
    public ResponseEntity<Object> detailKelas(@PathVariable int kelasId) {

        Kelas updatedKelas = kelasService.getById(kelasId);
        return ResponseUtil.okResponse(KelasMapper.toDetailDto(updatedKelas, updatedKelas.getListsesiKelas()),
                "Success");
    }

    @PutMapping("/kelas/{kelasId}")
    public ResponseEntity<Object> updateKelas(@Valid @RequestBody UpdateKelas updateKelasRequest,
            @PathVariable int kelasId) {

        updateKelasRequest.setId(kelasId);
        Kelas updatedKelas = kelasService.update(updateKelasRequest);
        return ResponseUtil.okResponse(KelasMapper.toDetailDto(updatedKelas, updatedKelas.getListsesiKelas()),
                "Kelas dengan id " + updatedKelas.getKelasId() + " berhasil diupdate");
    }

    @DeleteMapping("/kelas/{kelasId}")
    public ResponseEntity<Object> deleteKelas(@PathVariable int kelasId) {

        kelasService.delete(kelasId);
        return ResponseUtil.okResponse(null, "Kelas dengan id " + kelasId + " berhasil dihapus");
    }
}
