package com.nakahama.simpenbackend.User.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nakahama.simpenbackend.Auth.service.AuthService;
import com.nakahama.simpenbackend.User.dto.availability.ReadAvailability;
import com.nakahama.simpenbackend.User.dto.availability.UpdateAvailabilityRequest;
import com.nakahama.simpenbackend.User.model.Pengajar;
import com.nakahama.simpenbackend.User.service.AvailabilityService;
import com.nakahama.simpenbackend.util.ResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/availability")
public class AvailabilityController {
    @Autowired
    AuthService authService;

    @Autowired
    AvailabilityService availabilityService;

    @SuppressWarnings("deprecation")
    @PutMapping("")
    public ResponseEntity<Object> updateAvailability(@Valid @RequestBody List<UpdateAvailabilityRequest> listJadwalAvailability, HttpServletRequest request) {
        Pengajar pengajar = (Pengajar)authService.getLoggedUser(request);
        availabilityService.updateAvailability(listJadwalAvailability, pengajar);

        return ResponseUtil.okResponse(null, "Success");
    }

    @SuppressWarnings("deprecation")
    @GetMapping("")
    public ResponseEntity<Object> getAvailability(HttpServletRequest request) {
        Pengajar pengajar = (Pengajar)authService.getLoggedUser(request);
        ReadAvailability listJadwalAvailability = availabilityService.getAvailability(pengajar);

        return ResponseUtil.okResponse(listJadwalAvailability, "Success");
    }
}
