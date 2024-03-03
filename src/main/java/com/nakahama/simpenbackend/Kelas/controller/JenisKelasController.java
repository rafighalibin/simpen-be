package com.nakahama.simpenbackend.Kelas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.nakahama.simpenbackend.Auth.service.AuthService;
import com.nakahama.simpenbackend.Kelas.dto.CreateJenisKelasRequestDTO;
import com.nakahama.simpenbackend.Kelas.model.JenisKelas;
import com.nakahama.simpenbackend.Kelas.service.JenisKelasService;
import com.nakahama.simpenbackend.User.model.Akademik;
import com.nakahama.simpenbackend.User.model.UserModel;
import com.nakahama.simpenbackend.User.service.UserService;
import com.nakahama.simpenbackend.util.BaseResponse;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/kelas/jenis")
public class JenisKelasController {

    @Autowired
    JenisKelasService jenisKelasService;

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    //helper
    @GetMapping("/moda-pertemuan")
    public BaseResponse getModaPertemuan(HttpServletRequest request) {

        @SuppressWarnings("deprecation")
        UserModel user = authService.getLoggedUser(request);

        if(user == null) {
            BaseResponse response = new BaseResponse();
            response.setCode(401);
            response.setStatus("Unauthorized");
            response.setMessage("Unauthorized");
            return response;
        }

        if(!user.getRole().equals("operasional")) {
            BaseResponse response = new BaseResponse();
            response.setCode(403);
            response.setStatus("Forbidden");
            response.setMessage("Forbidden");
            return response;
        }

        List<String> modaPertemuan = new ArrayList<>();
        jenisKelasService.findDistinctModaPertemuan().forEach(modaPertemuan::add);

        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setStatus("OK");
        response.setMessage("Success");
        response.setContent(modaPertemuan);
        return response;
    }

    @PostMapping("/")
    public BaseResponse createJenisKelas(@RequestBody CreateJenisKelasRequestDTO jenisKelasDTO,
                                        HttpServletRequest request) {

        @SuppressWarnings("deprecation")
        UserModel user = authService.getLoggedUser(request);

        if(user == null) {
            BaseResponse response = new BaseResponse();
            response.setCode(401);
            response.setStatus("Unauthorized");
            response.setMessage("Unauthorized");
            return response;
        }

        if(!user.getRole().equals("operasional")) {
            BaseResponse response = new BaseResponse();
            response.setCode(403);
            response.setStatus("Forbidden");
            response.setMessage("Forbidden");
            return response;
        }
        
        UserModel userAkademik = userService.getUserById(jenisKelasDTO.getPicAkademik());
        Akademik akademik = userAkademik.getAkademik();

        System.out.println(akademik.getId());

        if(userAkademik == null || akademik == null) {
            BaseResponse response = new BaseResponse();
            response.setCode(404);
            response.setStatus("Not Found");
            response.setMessage("User akademik not found");
            return response;
        }

        if(jenisKelasDTO.getModaPertemuan().isEmpty() || jenisKelasDTO.getTipe().isEmpty() || jenisKelasDTO.getBahasa().isEmpty()) {
            BaseResponse response = new BaseResponse();
            response.setCode(400);
            response.setStatus("Bad Request");
            response.setMessage("Bad Request");
            return response;
        }

        List<JenisKelas> jenisKelas = new ArrayList<>();

        for(String pertemuanValue : jenisKelasDTO.getModaPertemuan()) {
            for(String tipeValue : jenisKelasDTO.getTipe()){
                for(String bahasaValue : jenisKelasDTO.getBahasa()){
                    JenisKelas jenisKelasRequest = new JenisKelas();
                    jenisKelasRequest.setNama(jenisKelasDTO.getNama());
                    jenisKelasRequest.setModaPertemuan(pertemuanValue);
                    jenisKelasRequest.setTipe(tipeValue);
                    jenisKelasRequest.setBahasa(bahasaValue);
                    jenisKelasRequest.setPicAkademik(akademik);
                    jenisKelasService.save(jenisKelasRequest);
                    jenisKelas.add(jenisKelasRequest);
                }
            }
        }

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
    public BaseResponse updateJenisKelas(@RequestBody JenisKelas jenisKelasRequest, @RequestBody String entity) {

        // TODO: Add error handling

        JenisKelas jenisKelas = jenisKelasService.update(jenisKelasRequest);
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setStatus("OK");
        response.setMessage("Success");
        response.setContent(jenisKelas);
        return response;
    }

    @DeleteMapping(value = "/")
    public BaseResponse deleteJenisKelas(@RequestBody UUID id) {

        jenisKelasService.delete(id);

        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setStatus("OK");
        response.setMessage("Success");
        return response;
    }

}
