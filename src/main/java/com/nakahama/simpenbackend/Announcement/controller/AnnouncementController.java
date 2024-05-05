package com.nakahama.simpenbackend.Announcement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nakahama.simpenbackend.Announcement.dto.CreateAnnouncementDTO;
import com.nakahama.simpenbackend.Announcement.model.Announcement;
import com.nakahama.simpenbackend.Announcement.repository.AnnouncementDb;
import com.nakahama.simpenbackend.Announcement.service.AnnouncementService;
import com.nakahama.simpenbackend.util.ResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    @Autowired
    AnnouncementService announcementService;

    @Autowired
    AnnouncementDb announcementDb;

    @PostMapping("")
    public ResponseEntity<Object> addAnnouncement(@Valid @RequestBody CreateAnnouncementDTO createAnnouncementDTO,
            HttpServletRequest request) {
        Announcement newAnnouncement = announcementService.addAnnouncement(request, createAnnouncementDTO);
        return ResponseUtil.okResponse(newAnnouncement, "Success");
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllAnnouncement() {
        List<Announcement> listAnouncement = announcementService.retrieveAllAnnouncement();
        return ResponseUtil.okResponse(listAnouncement, "Success");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAnnouncement(@PathVariable("id") String id) {
        Announcement announcement = announcementService.getAnnouncementById(Long.parseLong(id));
        return ResponseUtil.okResponse(announcement, "Success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAnnouncement(@PathVariable("id") String id) {
        announcementService.deleteAnnouncement(Long.parseLong(id));
        return ResponseUtil.okResponse(null, "Announcement dengan id " + id + " berhasil dihapus");
    }

}
