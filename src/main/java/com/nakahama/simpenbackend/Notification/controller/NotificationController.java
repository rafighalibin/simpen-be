package com.nakahama.simpenbackend.Notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nakahama.simpenbackend.Notification.dto.GenerateNotifDTO;
import com.nakahama.simpenbackend.Notification.dto.SetStatusNotifDTO;
import com.nakahama.simpenbackend.Notification.model.Notification;
import com.nakahama.simpenbackend.Notification.service.NotificationService;
import com.nakahama.simpenbackend.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @PostMapping("")
    public ResponseEntity<Object> testAddNotif(@RequestBody GenerateNotifDTO generateNotifDTO) {
        Notification notifikasi = notificationService.generateNotification(generateNotifDTO);
        return ResponseUtil.okResponse(notifikasi, "Success");
    }

    @PostMapping("/set_status")
    public ResponseEntity<Object> setNotifStatus(@RequestBody SetStatusNotifDTO setStatusNotifDTO) {
        notificationService.setNotifStatus(setStatusNotifDTO);
        return ResponseUtil.okResponse(null, "Success");
    }

    @GetMapping("")
    public ResponseEntity<Object> getAll() {
        List<Notification> listNotif = notificationService.retreiveAllNotification();
        return ResponseUtil.okResponse(listNotif, "Success");

    }

}
