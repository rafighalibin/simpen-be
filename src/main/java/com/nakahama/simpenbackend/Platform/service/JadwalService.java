package com.nakahama.simpenbackend.Platform.service;

import com.nakahama.simpenbackend.Kelas.model.SesiKelas;
import com.nakahama.simpenbackend.Platform.model.JadwalZoom;
import com.nakahama.simpenbackend.Platform.model.Platform;
import com.nakahama.simpenbackend.Platform.model.Zoom;

import java.util.List;
import java.util.UUID;

public interface JadwalService {
    public Zoom findAvalaibleZoom(List<SesiKelas> listSesiKelas);

    public void save(JadwalZoom jadwalZoom);

    public List<List<Platform>> getAvalaibleZoom(int uuid);

    public void deleteById(UUID jadwalZoomId);
}
