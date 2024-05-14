package com.nakahama.simpenbackend.PerubahanKelas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nakahama.simpenbackend.PerubahanKelas.service.GantiPengajarService;
import com.nakahama.simpenbackend.User.model.UserModel;
import com.nakahama.simpenbackend.User.service.UserService;
import com.nakahama.simpenbackend.util.ResponseUtil;
import com.nakahama.simpenbackend.Kelas.model.SesiKelas;
import com.nakahama.simpenbackend.Kelas.service.SesiKelasService;
import com.nakahama.simpenbackend.Notification.dto.GenerateNotifDTO;
import com.nakahama.simpenbackend.Notification.service.NotificationService;
import com.nakahama.simpenbackend.PerubahanKelas.dto.GantiPengajar.CreateGantiPengajar;
import com.nakahama.simpenbackend.PerubahanKelas.dto.GantiPengajar.GantiPengajarMapper;
import com.nakahama.simpenbackend.PerubahanKelas.dto.GantiPengajar.UpdateGantiPengajar;
import com.nakahama.simpenbackend.PerubahanKelas.model.PengajarMenggantikan;

import jakarta.validation.Valid;
import java.util.*;
import java.util.UUID;

@RestController
@RequestMapping("/ganti-pengajar")
public class GantiPengajarController {

    @Autowired
    GantiPengajarService gantiPengajarService;

    @Autowired
    SesiKelasService sesiKelasService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    UserService userService;

    @PostMapping("/{kelasId}")
    public ResponseEntity<Object> createGantiPengajar(
            @Valid @RequestBody List<CreateGantiPengajar> listGantiPengajarRequest) {
        gantiPengajarService.save(listGantiPengajarRequest);

        // Generate Notification
        List<UserModel> listOps = userService.getAllOperasional();

        for (UserModel userModel : listOps) {
            GenerateNotifDTO notification = new GenerateNotifDTO();
            notification.setAkunPenerima(userModel.getId());
            notification.setTipe(6);

            // Content of Notification
            notification.setJudul("pengajuan perubahan pengajar sesi kelas");
            notification.getIsi().put("sesiKelas", String.valueOf(listGantiPengajarRequest));

            notificationService.generateNotification(notification);
        }

        return ResponseUtil.okResponse(null, listGantiPengajarRequest.size() + " Ganti Pengajar Request created");
    }

    @GetMapping("/{kelasId}")
    public ResponseEntity<Object> getAllRescheduleByKelas(@PathVariable(value= "kelasId") int kelasId) {
        List<SesiKelas> response = sesiKelasService.getByKelasId(kelasId);
        return ResponseUtil.okResponse(GantiPengajarMapper.toReadDetailGantiPengajar(response), "Success");
    }

    @PutMapping("/{kelasId}")
    public ResponseEntity<Object> approveGantiPengajar(
            @Valid @RequestBody List<UpdateGantiPengajar> listGantiPengajarRequest) {
        gantiPengajarService.approve(listGantiPengajarRequest);

        // Generate Notification for requestee
        for (UpdateGantiPengajar request : listGantiPengajarRequest) {
            GenerateNotifDTO notification = new GenerateNotifDTO();
            GenerateNotifDTO notification1 = new GenerateNotifDTO();
            PengajarMenggantikan pengajarMenggantikan = gantiPengajarService.getById(request.getId());
            String namaPengajarMenggantikan = userService
                    .getUserById(pengajarMenggantikan.getPengajarPenganti().getId()).getNama();
            UUID idPengajarRequest = pengajarMenggantikan.getSesiKelas().getPengajar().getId();

            notification.setAkunPenerima(idPengajarRequest);
            notification1.setAkunPenerima(pengajarMenggantikan.getPengajarPenganti().getId());
            notification.setTipe(3);
            notification1.setTipe(0);

            // Content of Notification
            if (request.getPengajarPenggantiId() != null) {
                notification.setJudul("Permintaan pengajar pengganti disetujui");
                notification1.setJudul("Anda mendapatkan jadwal sesi kelas baru");
                notification.getIsi().put("status", "disetujui");
                notification.getIsi().put("sesiKelas", String.valueOf(pengajarMenggantikan.getSesiKelas().getId()));
                notification.getIsi().put("pengganti", String.valueOf(namaPengajarMenggantikan));
                notification.getIsi().put("idKelas",
                        String.valueOf(pengajarMenggantikan.getSesiKelas().getKelas().getKelasId()));
                notification.getIsi().put("waktuSesi",
                        String.valueOf(pengajarMenggantikan.getSesiKelas().getWaktuPelaksanaan()));
                notification1.getIsi().put("idKelas",
                        String.valueOf(pengajarMenggantikan.getSesiKelas().getKelas().getKelasId()));
                notification1.getIsi().put("waktuSesi",
                        String.valueOf(pengajarMenggantikan.getSesiKelas().getWaktuPelaksanaan()));

                notificationService.generateNotification(notification);
                notificationService.generateNotification(notification1);
            } else {
                notification.getIsi().put("status", "ditolak");
                notification.setJudul("Permintaan pengajar pengganti ditolak");
                notification.getIsi().put("sesiKelas", String.valueOf(pengajarMenggantikan.getSesiKelas().getId()));
                notificationService.generateNotification(notification);

            }

        }

        // // Generate Notification for assigned
        // for (UpdateGantiPengajar request : listGantiPengajarRequest) {
        // GenerateNotifDTO notification = new GenerateNotifDTO();
        // PengajarMenggantikan pengajarMenggantikan =
        // gantiPengajarService.getById(request.getId());
        // String namaPengajarMenggantikan = userService
        // .getUserById(pengajarMenggantikan.getKelas().getPengajar().getId()).getNama();
        // UUID idPengajarRequest =
        // pengajarMenggantikan.getSesiKelas().getPengajar().getId();

        // notification.setAkunPenerima(idPengajarRequest);
        // notification.setTipe(4);

        // }

        return ResponseUtil.okResponse(null, listGantiPengajarRequest.size() + " Ganti Pengajar Request changed");
    }

}
