package com.nakahama.simpenbackend.Kelas.controller;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.nakahama.simpenbackend.Auth.service.AuthService;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.CreateJenisKelas;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.DeleteJenisKelas;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.JenisKelasMapper;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.FindJenisKelas;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.ProgramJenisKelasAttributes;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.ReadJenisKelas;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.UpdateJenisKelas;
import com.nakahama.simpenbackend.Kelas.model.JenisKelas;
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
    public ResponseEntity<Object> createOrUpdateJenisKelas(@Valid @RequestBody CreateJenisKelas jenisKelasRequest,
            HttpServletRequest request) {
        List<JenisKelas> existingJenisKelas = jenisKelasService.getByNama(jenisKelasRequest.getNama());
        if (existingJenisKelas.isEmpty()) {
            // Name not found, save as new
            jenisKelasService.save(jenisKelasRequest);

            // Generate Notification
            GenerateNotifDTO notification = new GenerateNotifDTO();
            notification.setAkunPenerima(jenisKelasRequest.getPicAkademikId());
            notification.setTipe(7);

            // Content of Notification
            notification.setJudul("Anda menjadi PIC jenis kelas " + String.valueOf(jenisKelasRequest.getNama()));

            notificationService.generateNotification(notification);
            return ResponseUtil.okResponse(null,
                    "Jenis Kelas with name " + jenisKelasRequest.getNama() + " has been created");
        } else {
            // Name found, update the existing record
            ReadJenisKelas updatedJenisKelas = jenisKelasService.createToUpdate(jenisKelasRequest);
            return ResponseUtil.okResponse(updatedJenisKelas,
                    "Jenis Kelas with name " + jenisKelasRequest.getNama() + " has been updated");
        }
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> getAllJenisKelas(@RequestHeader("Authorization") String token) {
        List<ReadJenisKelas> listJenisKelas = jenisKelasService.getAll();
        return ResponseUtil.okResponse(listJenisKelas, "Success");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getJenisKelasById(@PathVariable("id") String id) {
        ReadJenisKelas jenisKelas = jenisKelasService.getJenisKelasById(UUID.fromString(id));
        return ResponseUtil.okResponse(jenisKelas, "Success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteJenisKelasId(@PathVariable("id") String id) {
        jenisKelasService.delete(UUID.fromString(id));
        return ResponseUtil.okResponse(null, "Jenis Kelas dengan ID " + id + " has been deleted");
    }

    @GetMapping("/existing-attributes")
    public ResponseEntity<Object> getAllExistingAttributes() {
        Map<String, List<String>> existingAttributes = jenisKelasService.getAllExistingAttributes();
        return ResponseUtil.okResponse(existingAttributes, "Success");
    }

    @GetMapping("/find")
    public ResponseEntity<Object> findJenisKelas(@RequestParam("nama") String nama, @RequestParam("tipe") String tipe,
            @RequestParam("modaPertemuan") String modaPertemuan, @RequestParam("bahasa") String bahasa) {
        ReadJenisKelas jenisKelas = jenisKelasService.findJenisKelas(nama, tipe, modaPertemuan, bahasa);
        return ResponseUtil.okResponse(jenisKelas, "Success");
    }
}
