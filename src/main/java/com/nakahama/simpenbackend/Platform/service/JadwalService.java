package com.nakahama.simpenbackend.Platform.service;

import com.nakahama.simpenbackend.Kelas.model.SesiKelas;
import com.nakahama.simpenbackend.Platform.model.JadwalZoom;
import com.nakahama.simpenbackend.Platform.model.Zoom;

import java.util.List;

public interface JadwalService {
    public Zoom findAvalaibleZoom(List<SesiKelas> listSesiKelas);

    public void create(JadwalZoom jadwalZoom);
}
