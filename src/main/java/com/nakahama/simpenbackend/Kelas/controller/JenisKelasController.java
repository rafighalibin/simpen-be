package com.nakahama.simpenbackend.Kelas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.nakahama.simpenbackend.Kelas.model.JenisKelas;
import com.nakahama.simpenbackend.Kelas.service.JenisKelasService;
import com.nakahama.simpenbackend.util.BaseResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("/kelas/jenis")
public class JenisKelasController {

    @Autowired
    JenisKelasService jenisKelasService;

    @PostMapping("/")
    public BaseResponse createJenisKelas(@RequestBody JenisKelas jenisKelasRequest,
            @RequestHeader("Authorization") String token) {

        JenisKelas jenisKelas = new JenisKelas();
        jenisKelas.setNama(jenisKelasRequest.getNama());
        jenisKelas.setPertemuan(jenisKelasRequest.getPertemuan());
        jenisKelas.setTipe(jenisKelasRequest.getTipe());
        jenisKelas.setBahasa(jenisKelasRequest.getBahasa());
        jenisKelas.setPicAkademik(jenisKelasRequest.getPicAkademik());
        jenisKelas.setProgram(jenisKelasRequest.getProgram());

        jenisKelas = jenisKelasService.save(jenisKelas);

        // TODO: Add error handling
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setStatus("OK");
        response.setMessage("Success");
        response.setContent(jenisKelas);
        return response;
    }

    @GetMapping(value = "/")
    public BaseResponse getJenisKelas(@RequestHeader("Authorization") String token) {

        List<JenisKelas> listJenisKelas = jenisKelasService.getAll();

        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setStatus("OK");
        response.setMessage("Success");
        response.setContent(listJenisKelas);
        return response;
    }

    @PutMapping("/")
    public String updateJenisKelas(@RequestBody String entity) {
        // TODO: pathprocess PUT request

        return entity;
    }

}
