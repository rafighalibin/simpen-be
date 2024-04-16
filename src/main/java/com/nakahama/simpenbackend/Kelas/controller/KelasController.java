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
import com.nakahama.simpenbackend.Kelas.dto.Kelas.AddPlaylist;
import com.nakahama.simpenbackend.Kelas.dto.Kelas.CreateKelas;
import com.nakahama.simpenbackend.Kelas.dto.Kelas.KelasMapper;
import com.nakahama.simpenbackend.Kelas.dto.Kelas.ReadKelas;
import com.nakahama.simpenbackend.Kelas.dto.Kelas.UpdateKelas;
import com.nakahama.simpenbackend.Kelas.service.*;
import com.nakahama.simpenbackend.Notification.dto.GenerateNotifDTO;
import com.nakahama.simpenbackend.Notification.service.NotificationService;
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
    MuridKelasService muridKelasService;

    @Autowired
    ProgramServiceImpl programService;

    @Autowired
    JenisKelasService jenisKelasService;

    @Autowired
    SesiKelasService sesiKelasService;

    @Autowired
    NotificationService notificationService;

    @SuppressWarnings("deprecation")
    @GetMapping("/kelas")
    public ResponseEntity<Object> getKelas(
            HttpServletRequest request) {
        String role = authService.getRoleLoggedUser(request);
        List<ReadKelas> listKelas = new ArrayList<ReadKelas>();
        if (role.equals("pengajar")) {
            for (Kelas kelas : kelasService.getAllKelasPengajar(authService.getLoggedUser(request))) {
                listKelas.add(KelasMapper.toReadDto(kelas));
            }
        } else {
            for (Kelas kelas : kelasService.getAll()) {
                listKelas.add(KelasMapper.toReadDto(kelas));
            }
        }
        return ResponseUtil.okResponse(listKelas, "Success");
    }

    @SuppressWarnings("deprecation")
    @PostMapping("/kelas")
    public ResponseEntity<Object> createKelas(@Valid @RequestBody CreateKelas createKelasRequest,
            HttpServletRequest request) {
        createKelasRequest.setOperasional(authService.getLoggedUser(request));
        Kelas createdKelas = kelasService.save(createKelasRequest);

        // Generate Notification
        GenerateNotifDTO notification = new GenerateNotifDTO();
        notification.setAkunPenerima(createdKelas.getPengajar().getId());
        notification.setTipe(1);

        // Content of Notification
        notification.setJudul("Anda mendapatkan jadwal kelas baru");
        notification.getIsi().put("idKelas", String.valueOf(createdKelas.getKelasId()));
        notification.getIsi().put("jadwalKelas", String.valueOf(createKelasRequest.getJadwalKelas()));
        notification.getIsi().put("tanggalMulai", String.valueOf(createdKelas.getTanggalMulai()));
        notification.getIsi().put("tanggalSelesai", String.valueOf(createdKelas.getTanggalSelesai()));

        notificationService.generateNotification(notification);

        return ResponseUtil.okResponse(
                KelasMapper.toDetailDto(
                        createdKelas,
                        createdKelas.getListsesiKelas(),
                        userService.getUserById(createdKelas.getPengajar().getId()), createdKelas.getMuridKelas()),
                "Kelas dengan id " + createdKelas.getKelasId() + " berhasil dibuat");
    }

    @GetMapping("/kelas/{kelasId}")
    public ResponseEntity<Object> detailKelas(@PathVariable(value="kelasId") int kelasId) {

        Kelas updatedKelas = kelasService.getById(kelasId);
        return ResponseUtil.okResponse(KelasMapper.toDetailDto(updatedKelas, updatedKelas.getListsesiKelas(),
                userService.getUserById(updatedKelas.getPengajar().getId()), updatedKelas.getMuridKelas()),
                "Success");
    }

    @PutMapping("/kelas/{kelasId}")
    public ResponseEntity<Object> updateKelas(@Valid @RequestBody UpdateKelas updateKelasRequest,
            @PathVariable("kelasId") int kelasId) {

        updateKelasRequest.setId(kelasId);
        Kelas updatedKelas = kelasService.update(updateKelasRequest);
        return ResponseUtil.okResponse(KelasMapper.toDetailDto(updatedKelas, updatedKelas.getListsesiKelas(),
                userService.getUserById(updatedKelas.getPengajar().getId()), updatedKelas.getMuridKelas()),
                "Kelas dengan id " + updatedKelas.getKelasId() + " berhasil diupdate");
    }

    @PostMapping("/kelas/playlist/{kelasId}")
    public ResponseEntity<Object> addPlaylist(@PathVariable("kelasId") int kelasId, @Valid @RequestBody AddPlaylist linkPlaylist) {

        Kelas updatedKelas = kelasService.addPlaylist(kelasId, linkPlaylist.getLinkPlaylist());
        return ResponseUtil.okResponse(KelasMapper.toDetailDto(updatedKelas, updatedKelas.getListsesiKelas(),
                userService.getUserById(updatedKelas.getPengajar().getId()), updatedKelas.getMuridKelas()),
                "Success");
    }

    @DeleteMapping("/kelas/{kelasId}")
    public ResponseEntity<Object> deleteKelas(@PathVariable("kelasId") int kelasId) {

        kelasService.delete(kelasId);
        return ResponseUtil.okResponse(null, "Kelas dengan id " + kelasId + " berhasil dihapus");
    }
}
