package com.nakahama.simpenbackend.Kelas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.nakahama.simpenbackend.Auth.service.AuthService;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.CreateJenisKelas;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.DeleteJenisKelas;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.ReadJenisKelas;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.UpdateJenisKelas;
import com.nakahama.simpenbackend.Kelas.service.JenisKelasService;
import com.nakahama.simpenbackend.Notification.dto.GenerateNotifDTO;
import com.nakahama.simpenbackend.Notification.service.NotificationService;
import com.nakahama.simpenbackend.util.ResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/kelas/jenis")
public class JenisKelasController {

    @Autowired
    JenisKelasService jenisKelasService;

    @Autowired
    AuthService authService;

    @Autowired
    NotificationService notificationService;

    @PostMapping("")
    public ResponseEntity<Object> createJenisKelas(@Valid @RequestBody CreateJenisKelas jenisKelasRequest,
            HttpServletRequest request) {
        jenisKelasService.save(jenisKelasRequest);

        // Generate Notification
        GenerateNotifDTO notification = new GenerateNotifDTO();
        notification.setAkunPenerima(jenisKelasRequest.getPicAkademikId());
        notification.setTipe(7);

        // Content of Notification
        notification
                .setJudul("Anda menjadi PIC akademik untuk jenis kelas " + String.valueOf(jenisKelasRequest.getNama()));
        notification.getIsi().put("namaJenisKelas", String.valueOf(jenisKelasRequest.getNama()));

        notificationService.generateNotification(notification);

        return ResponseUtil.okResponse(null,
                "Jenis Kelas with name " + jenisKelasRequest.getNama() + " has been created");
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> getAllJenisKelas(@RequestHeader("Authorization") String token) {
        List<ReadJenisKelas> listJenisKelas = jenisKelasService.getAll();
        return ResponseUtil.okResponse(listJenisKelas, "Success");
    }

    @PutMapping("")
    public ResponseEntity<Object> updateJenisKelas(@Valid @RequestBody UpdateJenisKelas jenisKelasRequest) {
        ReadJenisKelas jenisKelas = jenisKelasService.update(jenisKelasRequest);
        return ResponseUtil.okResponse(jenisKelas,
                "Jenis Kelas with name " + jenisKelas.getNama() + " has been updated");
    }

    @DeleteMapping(value = "")
    public ResponseEntity<Object> deleteJenisKelas(@Valid @RequestBody DeleteJenisKelas jenisKelasRequest) {
        jenisKelasService.delete(jenisKelasRequest.getId());
        return ResponseUtil.okResponse(null,
                "Jenis Kelas dengan ID " + jenisKelasRequest.getId() + " has been deleted");

    }

    @GetMapping("/existing-attributes")
    public ResponseEntity<Object> getAllExistingAttributes() {
        Map<String, List<String>> existingAttributes = jenisKelasService.getAllExistingAttributes();
        return ResponseUtil.okResponse(existingAttributes, "Success");
    }

    @GetMapping("/existing-attributes-detail")
    public ResponseEntity<Object> getExistingAttributes(@RequestParam String nama) {
        Map<String, List<String>> existingAttributes = jenisKelasService.getExistingAttributes(nama);
        return ResponseUtil.okResponse(existingAttributes, "Success");
    }

    @GetMapping("/find")
    public ResponseEntity<Object> findJenisKelas(@RequestParam String nama, @RequestParam String tipe,
            @RequestParam String modaPertemuan, @RequestParam String bahasa) {
        ReadJenisKelas jenisKelas = jenisKelasService.findJenisKelas(nama, tipe, modaPertemuan, bahasa);
        return ResponseUtil.okResponse(jenisKelas, "Success");
    }
}
