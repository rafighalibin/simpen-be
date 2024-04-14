package com.nakahama.simpenbackend.Announcement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Announcement.dto.CreateAnnouncementDTO;
import com.nakahama.simpenbackend.Announcement.model.Announcement;
import com.nakahama.simpenbackend.Announcement.repository.AnnouncementDb;
import com.nakahama.simpenbackend.Auth.service.AuthService;
import com.nakahama.simpenbackend.User.model.UserModel;
import com.nakahama.simpenbackend.exception.BadRequestException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    AnnouncementDb announcementDb;

    @Autowired
    AuthService authService;

    @Override
    public List<Announcement> retrieveAllAnnouncement() {
        return announcementDb.findAll();
    }

    @Override
    @Deprecated
    public Announcement addAnnouncement(HttpServletRequest request, CreateAnnouncementDTO createAnnouncementDTO) {
        UserModel pembuat = authService.getLoggedUser(request);
        if (pembuat != null) {
            Announcement announcement = new Announcement();
            announcement.setNamaPembuat(pembuat.getNama());
            announcement.setRolePembuat(pembuat.getRole());
            announcement.setJudul(createAnnouncementDTO.getJudul());
            announcement.setIsi(createAnnouncementDTO.getIsi());
            announcementDb.save(announcement);
            return announcement;
        } else {
            return null;
        }
    }

    @Override
    public void deleteAnnouncement(Long id) {
        Announcement announcement = getAnnouncementById(id);
        if (announcement != null) {
            announcement.setDeleted(true);
            announcementDb.save(announcement);
        }

    }

    @Override
    public Announcement getAnnouncementById(Long id) {
        for (Announcement announcement : retrieveAllAnnouncement()) {
            if (announcement.getId() == id) {
                return announcement;
            }
        }
        throw new BadRequestException("Announcement with id " + id + " not found");
    }

}
