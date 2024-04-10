package com.nakahama.simpenbackend.Notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nakahama.simpenbackend.Notification.dto.GenerateNotifDTO;
import com.nakahama.simpenbackend.Notification.model.Notification;
import com.nakahama.simpenbackend.Notification.service.NotificationService;
import com.nakahama.simpenbackend.util.ResponseUtil;

import jakarta.validation.Valid;

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
}
