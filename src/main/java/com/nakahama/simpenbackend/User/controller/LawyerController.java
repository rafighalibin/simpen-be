package com.nakahama.simpenbackend.User.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.text.SimpleDateFormat;
import com.nakahama.simpenbackend.User.model.Lawyer;
import com.nakahama.simpenbackend.User.service.LawyerService;
import com.nakahama.simpenbackend.util.BaseResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class LawyerController {

    @Autowired
    LawyerService lawyerService;

    @GetMapping("/lawyer")
    public BaseResponse getSampleLawyer() {
        // Create a sample Lawyer object

        List<Lawyer> lawyers = lawyerService.getAll();
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setStatus("OK");
        response.setMessage("Success");
        response.setContent(lawyers);

        return response;
    }

    @PostMapping("/lawyer")
    public BaseResponse postMethodName(@RequestBody Lawyer Lawyer) {

        Lawyer lawyer = new Lawyer();
        lawyer.setUser_id(UUID.randomUUID());
        lawyer.setAlamat_ktp(Lawyer.getAlamat_ktp());
        lawyer.setDomisili_kota(Lawyer.getDomisili_kota());
        lawyer.setFoto_diri(null);
        lawyer.setEmail_pribadi(Lawyer.getEmail_pribadi());
        lawyer.setEmail_kalananti(Lawyer.getEmail_kalananti());
        lawyer.setBackup_phone_num(Lawyer.getBackup_phone_num());

        lawyer = lawyerService.save(lawyer);

        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setStatus("OK");
        response.setMessage("Success");
        response.setContent(lawyer);
        return response;
    }

}
