package com.nakahama.simpenbackend.PerubahanKelas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nakahama.simpenbackend.PerubahanKelas.service.RescheduleService;
import com.nakahama.simpenbackend.User.model.UserModel;
import com.nakahama.simpenbackend.User.service.UserService;
import com.nakahama.simpenbackend.util.ResponseUtil;
import com.nakahama.simpenbackend.Kelas.model.SesiKelas;
import com.nakahama.simpenbackend.Kelas.service.SesiKelasService;
import com.nakahama.simpenbackend.Notification.dto.GenerateNotifDTO;
import com.nakahama.simpenbackend.Notification.service.NotificationService;
import com.nakahama.simpenbackend.PerubahanKelas.dto.Reschedule.RescheduleMapper;
import com.nakahama.simpenbackend.PerubahanKelas.dto.Reschedule.CreateReschedule;

import jakarta.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/reschedule")
public class RescheduleController {

    @Autowired
    RescheduleService rescheduleService;

    @Autowired
    SesiKelasService sesiKelasService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    UserService userService;

    @PostMapping("/create/{kelasId}")
    public ResponseEntity<Object> createReschedule(@Valid @RequestBody List<CreateReschedule> rescheduleRequest) {
        rescheduleService.save(rescheduleRequest);

        // Generate Notification
        List<UserModel> listOps = userService.getAllOperasional();

        for (UserModel userModel : listOps) {
            GenerateNotifDTO notification = new GenerateNotifDTO();
            notification.setAkunPenerima(userModel.getId());
            notification.setTipe(5);

            // Content of Notification
            notification.setJudul("pengajuan perubahan jadwal sesi kelas");
            notification.getIsi().put("sesiKelas", String.valueOf(rescheduleRequest));

            notificationService.generateNotification(notification);
        }

        return ResponseUtil.okResponse(null, rescheduleRequest.size() + " Reschedule created");
    }

    @GetMapping("/{kelasId}")
    public ResponseEntity<Object> getAllRescheduleByKelas(@PathVariable int kelasId) {
        List<SesiKelas> response = sesiKelasService.getByKelasId(kelasId);
        return ResponseUtil.okResponse(RescheduleMapper.toReadDetailReschedule(response), "Success");
    }

}
