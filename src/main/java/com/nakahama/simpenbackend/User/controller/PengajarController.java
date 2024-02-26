package com.nakahama.simpenbackend.User.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.text.SimpleDateFormat;
import com.nakahama.simpenbackend.User.model.Pengajar;

@RestController
public class PengajarController {

    // TODO: set the origin to the frontend URL globally
    @CrossOrigin(origins = "https://simpen-fe.vercel.app/")
    @GetMapping("/pengajar")
    public Pengajar getSamplePengajar() {
        // Create a sample Pengajar object
        Pengajar pengajar = new Pengajar();
        pengajar.setUser_id(UUID.randomUUID()); // Generate a random UUID for user_id
        pengajar.setAlamat_ktp("Jl. KTP No. 123");
        pengajar.setDomisili_kota("Jakarta");
        pengajar.setFoto_diri(null); // You can set a sample image here if you have one
        pengajar.setEmail_pribadi("pengajar@example.com");
        pengajar.setEmail_kalananti("kalananti@example.com");
        pengajar.setBackup_phone_num(generateBackupPhoneNumber());

        return pengajar;
    }

    private String generateBackupPhoneNumber() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyHHmmss");
        String formattedDate = dateFormat.format(new Date());
        return formattedDate;
    }

}
