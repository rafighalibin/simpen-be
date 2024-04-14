package com.nakahama.simpenbackend.PerubahanKelas.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.model.Kelas;
import com.nakahama.simpenbackend.Kelas.model.SesiKelas;
import com.nakahama.simpenbackend.Kelas.service.KelasService;
import com.nakahama.simpenbackend.Kelas.service.SesiKelasService;
import com.nakahama.simpenbackend.PerubahanKelas.dto.Reschedule.RescheduleMapper;
import com.nakahama.simpenbackend.PerubahanKelas.dto.Reschedule.UpdateReschedule;
import com.nakahama.simpenbackend.PerubahanKelas.dto.Reschedule.CreateReschedule;
import com.nakahama.simpenbackend.PerubahanKelas.model.Reschedule;
import com.nakahama.simpenbackend.PerubahanKelas.repository.RescheduleDb;
import com.nakahama.simpenbackend.Platform.model.JadwalZoom;
import com.nakahama.simpenbackend.Platform.model.Zoom;
import com.nakahama.simpenbackend.Platform.service.JadwalService;
import com.nakahama.simpenbackend.Platform.service.PlatformService;

@Service
public class RescheduleServiceImpl implements RescheduleService {

    @Autowired
    RescheduleDb rescheduleDb;

    @Autowired
    SesiKelasService sesiKelasService;

    @Autowired
    KelasService kelasService;

    @Autowired
    PlatformService platformService;

    @Autowired
    JadwalService jadwalService;

    @Override
    public List<Reschedule> getAll() {
        return rescheduleDb.findAll();
    }

    @Override
    public void save(List<CreateReschedule> rescheduleRequest) {
        List<Reschedule> reschedulesToCreate = new ArrayList<>();
        for (CreateReschedule request : rescheduleRequest) {
            SesiKelas sesiKelasRequested = sesiKelasService.getById(request.getSesiKelasId());
            if (!sesiKelasRequested.getStatus().equals("Scheduled")) {
                throw new IllegalArgumentException(
                        "SesiKelas with id " + request.getSesiKelasId() + " is not scheduled");
            }
            reschedulesToCreate.add(RescheduleMapper.toEntity(request, sesiKelasRequested));
        }
        for (Reschedule reschedule : reschedulesToCreate) {
            reschedule.getSesiKelas().setStatus("Reschedule Requested");
            rescheduleDb.save(reschedule);
        }

    }

    @Override
    public Reschedule getById(UUID id) {
        Reschedule reschedule = rescheduleDb.findById(id).orElse(null);
        if (reschedule == null)
            throw new NoSuchElementException("Reschedule with id " + id + " not found");

        return reschedule;
    }

    @Override
    public List<Reschedule> getAllByKelasId(int kelasId) {
        Kelas kelas = kelasService.getById(kelasId);
        return rescheduleDb.findAllByKelas(kelas);
    }

    @Override
    public void approve(List<UpdateReschedule> listRescheduleRequest) {
        for (UpdateReschedule request : listRescheduleRequest) {
            Reschedule reschedule = getById(request.getId());
            if (request.getZoomId() != null) {
                reschedule.getSesiKelas().getJadwalZoom().setSesiKelas(null);
                jadwalService.save(reschedule.getSesiKelas().getJadwalZoom());

                reschedule.getSesiKelas().setJadwalZoom(null);
                sesiKelasService.save(reschedule.getSesiKelas());

                JadwalZoom jadwalZoom = new JadwalZoom();
                Zoom zoom = (Zoom) platformService.getById(request.getZoomId());
                jadwalZoom.setZoom(zoom);
                jadwalZoom.setWaktu(reschedule.getWaktuBaru());
                jadwalZoom.setSesiKelas(reschedule.getSesiKelas());
                jadwalService.save(jadwalZoom);

                reschedule.getSesiKelas().setJadwalZoom(jadwalZoom);
                reschedule.getSesiKelas().setStatus("Scheduled");
                reschedule.getSesiKelas().setWaktuPelaksanaan(reschedule.getWaktuBaru());
                sesiKelasService.save(reschedule.getSesiKelas());

                reschedule.setStatus("Approved");
            } else {
                reschedule.getSesiKelas().setStatus("Scheduled");
                reschedule.setStatus("Rejected");
            }
            rescheduleDb.save(reschedule);
        }
    }

}
