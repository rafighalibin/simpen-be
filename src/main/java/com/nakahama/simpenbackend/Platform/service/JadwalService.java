package com.nakahama.simpenbackend.Platform.service;

import java.time.LocalDateTime;

import com.nakahama.simpenbackend.Kelas.model.SesiKelas;
import com.nakahama.simpenbackend.Platform.dto.ReadJadwal;
import com.nakahama.simpenbackend.Platform.model.JadwalRuangan;
import com.nakahama.simpenbackend.Platform.model.JadwalZoom;
import com.nakahama.simpenbackend.Platform.model.Platform;
import com.nakahama.simpenbackend.Platform.model.Ruangan;
import com.nakahama.simpenbackend.Platform.model.Zoom;

import java.util.List;
import java.util.UUID;

public interface JadwalService {
    public Zoom findAvalaibleZoom(List<SesiKelas> listSesiKelas);

    public void save(JadwalZoom jadwalZoom);

    public void save(JadwalRuangan jadwalRuangan);

    public List<List<Platform>> getAvalaibleZoom(int uuid);

    public void deleteById(UUID jadwalZoomId);

    public Zoom findAvailableZoomByDateTime(List<LocalDateTime> dateTime);

    public Ruangan findAvailableRuanganByDateTime(List<LocalDateTime> dateTime, String cabang);

    public List<ReadJadwal> getAllJadwal();
}
