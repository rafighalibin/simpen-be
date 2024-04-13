package com.nakahama.simpenbackend.Payroll.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nakahama.simpenbackend.Auth.service.AuthService;
import com.nakahama.simpenbackend.Kelas.service.SesiKelasService;
import com.nakahama.simpenbackend.Payroll.dto.AbsenPengajarMapper;
import com.nakahama.simpenbackend.Payroll.dto.ReadAbsenPengajar;
import com.nakahama.simpenbackend.Payroll.dto.createAbsenPengajarDTO;
import com.nakahama.simpenbackend.Payroll.model.AbsenPengajar;
import com.nakahama.simpenbackend.Payroll.service.AbsenPengajarService;
import com.nakahama.simpenbackend.User.model.Pengajar;
import com.nakahama.simpenbackend.util.ResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/absen-pengajar")
public class AbsenPengajarController {
    
    @Autowired
    AbsenPengajarService absenPengajarService;

    @Autowired
    SesiKelasService sesiKelasService;

    @Autowired
    AuthService authService;

    @SuppressWarnings("deprecation")
    @PostMapping("")
    public ResponseEntity<Object> createAbsen(@Valid @RequestBody createAbsenPengajarDTO absenPengajarDTO,HttpServletRequest request) {
        absenPengajarDTO.setPengajar((Pengajar)(authService.getLoggedUser(request)));
        absenPengajarService.createAbsen(absenPengajarDTO);

        return ResponseUtil.okResponse(absenPengajarDTO, "Success");

    }

    @GetMapping("")
    public ResponseEntity<Object> getAllAbsen() {
        List<ReadAbsenPengajar> listAbsenPengajar = new ArrayList<ReadAbsenPengajar>();
        for (AbsenPengajar absenPengajar : absenPengajarService.getAllAbsenPengajar()) {
            listAbsenPengajar.add(AbsenPengajarMapper.toReadDto(absenPengajar));
        }
        return ResponseUtil.okResponse(listAbsenPengajar, "Success");
    }
}
