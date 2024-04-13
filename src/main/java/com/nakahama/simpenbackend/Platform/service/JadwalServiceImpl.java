package com.nakahama.simpenbackend.Platform.service;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.model.SesiKelas;
import com.nakahama.simpenbackend.Platform.model.JadwalZoom;
import com.nakahama.simpenbackend.Platform.model.Platform;
import com.nakahama.simpenbackend.Platform.model.Zoom;
import com.nakahama.simpenbackend.Platform.repository.JadwalZoomDb;
import com.nakahama.simpenbackend.exception.BadRequestException;

@Service
public class JadwalServiceImpl implements JadwalService {
    @Autowired
    JadwalZoomDb jadwalZoomDb;

    @Override
    public Zoom findAvalaibleZoom(List<SesiKelas> listSesiKelas) {
        LocalDateTime waktu = listSesiKelas.get(0).getWaktuPelaksanaan();
        List<Platform> listZoom = jadwalZoomDb.checkJadwal(waktu, waktu.plusMinutes(90));
        List<Platform> platformToRemove = new ArrayList<>();
        for (SesiKelas sesiKelas : listSesiKelas) {
            for (Platform zoom : listZoom) {
                List<JadwalZoom> listJadwalZoom = jadwalZoomDb.checkJadwalForZoom(sesiKelas.getWaktuPelaksanaan(),
                        sesiKelas.getWaktuPelaksanaan().plusMinutes(90), (Zoom) zoom);
                if (listJadwalZoom.size() != 0)
                    platformToRemove.add(zoom);
            }
            for (Platform platform : platformToRemove)
                listZoom.remove(platform);
        }

        if (listZoom.size() == 0)
            throw new BadRequestException("Tidak ada zoom yang tersedia");

        return (Zoom) listZoom.get(0);
    }

    @Override
    public void create(JadwalZoom jadwalZoom) {
        jadwalZoomDb.save(jadwalZoom);
    }

}
