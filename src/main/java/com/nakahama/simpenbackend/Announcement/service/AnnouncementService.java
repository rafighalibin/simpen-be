package com.nakahama.simpenbackend.Announcement.service;

import java.util.List;

import com.nakahama.simpenbackend.Announcement.model.Announcement;
import com.nakahama.simpenbackend.Announcement.dto.CreateAnnouncementDTO;

import jakarta.servlet.http.HttpServletRequest;

public interface AnnouncementService {

    public List<Announcement> retrieveAllAnnouncement();

    public Announcement addAnnouncement(HttpServletRequest request, CreateAnnouncementDTO createAnnouncementDTO);

    public Announcement getAnnouncementById(Long id);

    public void deleteAnnouncement(Long id);
}
