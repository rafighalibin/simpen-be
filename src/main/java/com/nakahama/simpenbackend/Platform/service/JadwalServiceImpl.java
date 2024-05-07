package com.nakahama.simpenbackend.Platform.service;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.model.SesiKelas;
import com.nakahama.simpenbackend.Kelas.service.SesiKelasService;
import com.nakahama.simpenbackend.PerubahanKelas.model.Reschedule;
import com.nakahama.simpenbackend.Platform.model.JadwalRuangan;
import com.nakahama.simpenbackend.Platform.model.JadwalZoom;
import com.nakahama.simpenbackend.Platform.model.Platform;
import com.nakahama.simpenbackend.Platform.model.Zoom;
import com.nakahama.simpenbackend.Platform.repository.JadwalRuanganDb;
import com.nakahama.simpenbackend.Platform.repository.JadwalZoomDb;
import com.nakahama.simpenbackend.exception.BadRequestException;

@Service
public class JadwalServiceImpl implements JadwalService {
    @Autowired
    JadwalRuanganDb jadwalRuanganDb;

    @Autowired
    JadwalZoomDb jadwalZoomDb;

    @Autowired
    SesiKelasService sesiKelasService;

    @Override
    public Zoom findAvalaibleZoom(List<SesiKelas> listSesiKelas) {
        LocalDateTime waktu = listSesiKelas.get(0).getWaktuPelaksanaan();
        List<Platform> listZoom = jadwalZoomDb.checkJadwal(waktu, waktu.plusMinutes(90));
        List<Platform> platformToRemove = new ArrayList<>();
        for (SesiKelas sesiKelas : listSesiKelas) {
            for (Platform zoom : listZoom) {
                try{
                    List<JadwalZoom> listJadwalZoom = jadwalZoomDb.checkJadwalForZoom(sesiKelas.getWaktuPelaksanaan(),
                        sesiKelas.getWaktuPelaksanaan().plusMinutes(90), (Zoom) zoom);
                    if (listJadwalZoom.size() != 0)
                        platformToRemove.add(zoom);
                } catch (Exception e) {
                    continue;
                }
            }
            for (Platform platform : platformToRemove)
                listZoom.remove(platform);
        }

        if (listZoom.size() == 0)
            throw new BadRequestException("Tidak ada zoom yang tersedia");

        return (Zoom) listZoom.get(0);
    }

    @Override
    public Zoom findAvailableZoomByDateTime(List<LocalDateTime> listWaktu) {
        LocalDateTime waktu = listWaktu.get(0);
        List<Platform> listZoom = jadwalZoomDb.checkJadwal(waktu, waktu.plusMinutes(90));
        List<Platform> platformToRemove = new ArrayList<>();
        for (LocalDateTime dateTime : listWaktu) {
            for (Platform zoom : listZoom) {
                try{
                    List<JadwalZoom> listJadwalZoom = jadwalZoomDb.checkJadwalForZoom(dateTime,
                        dateTime.plusMinutes(90), (Zoom) zoom);
                    if (listJadwalZoom.size() != 0)
                        platformToRemove.add(zoom);
                } catch (Exception e) {
                    continue;
                }
            }
            for (Platform platform : platformToRemove)
                listZoom.remove(platform);
        }

        if (listZoom.size() == 0)
            throw new BadRequestException("Tidak ada zoom yang tersedia");

        return (Zoom) listZoom.get(0);
    }

    @Override
    public void save(JadwalZoom jadwalZoom) {
        jadwalZoomDb.save(jadwalZoom);
    }

    @Override
    public void save(JadwalRuangan jadwalRuangan) {
        jadwalRuanganDb.save(jadwalRuangan);
    }

    @Override
    public List<List<Platform>> getAvalaibleZoom(int kelasId) {
        List<List<Platform>> result = new ArrayList<>();
        List<SesiKelas> listSesiKelas = sesiKelasService.getByKelasId(kelasId);
        for (SesiKelas sesiKelas : listSesiKelas) {
            List<Platform> listZoom = new ArrayList<>();
            if (sesiKelas.getListReschedule().size() > 0) {
                if (sesiKelas.getListReschedule().get(sesiKelas.getListReschedule().size() - 1).getStatus()
                        .equals("Requested")) {
                    Reschedule activeReschedule = sesiKelas.getListReschedule()
                            .get(sesiKelas.getListReschedule().size() - 1);
                    listZoom = jadwalZoomDb.checkJadwalForSesi(activeReschedule.getWaktuBaru().minusMinutes(5),
                            activeReschedule.getWaktuBaru().plusMinutes(90), sesiKelas.getId());
                }
            }
            result.add(listZoom);
        }
        return result;
    }

    @Override
    public void deleteById(UUID jadwalZoomId) {
        jadwalZoomDb.deleteById(jadwalZoomId);
        jadwalZoomDb.flush();
    }

}
