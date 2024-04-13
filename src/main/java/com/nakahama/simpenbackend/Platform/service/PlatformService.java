package com.nakahama.simpenbackend.Platform.service;

import java.util.List;
import java.util.UUID;

import com.nakahama.simpenbackend.Kelas.model.SesiKelas;
import com.nakahama.simpenbackend.Platform.dto.Ruangan.CreateRuanganRequest;
import com.nakahama.simpenbackend.Platform.dto.Ruangan.UpdateRuanganRequest;
import com.nakahama.simpenbackend.Platform.dto.Zoom.CreateZoomRequest;
import com.nakahama.simpenbackend.Platform.dto.Zoom.UpdateZoomRequest;
import com.nakahama.simpenbackend.Platform.model.JadwalRuangan;
import com.nakahama.simpenbackend.Platform.model.JadwalZoom;
import com.nakahama.simpenbackend.Platform.model.Platform;
import com.nakahama.simpenbackend.Platform.model.Ruangan;
import com.nakahama.simpenbackend.Platform.model.Zoom;

public interface PlatformService {
        public Platform save(CreateZoomRequest createZoomRequest);

        public Platform save(CreateRuanganRequest CreateRuanganRequest);

        public List<Zoom> getAllZoom();

        public List<Ruangan> getAllRuangan();

        public Platform getById(UUID id);

        public List<Zoom> getAllZoomByNama(String nama);

        public List<Ruangan> getAllRuanganByNamaAndCabang(String nama, String cabang);

        public Platform update(UpdateRuanganRequest updateRuanganRequest);

        public Platform update(UpdateZoomRequest updateZoomRequest);

        public void delete(UUID id);

        public List<JadwalZoom> assignZoom(List<SesiKelas> sesiKelas);

        public List<JadwalRuangan> assignRuangan(List<SesiKelas> listSesiKelas);
}
