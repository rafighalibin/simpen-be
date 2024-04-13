package com.nakahama.simpenbackend.Platform.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.model.SesiKelas;
import com.nakahama.simpenbackend.Platform.dto.Ruangan.CreateRuanganRequest;
import com.nakahama.simpenbackend.Platform.dto.Ruangan.RuanganMapper;
import com.nakahama.simpenbackend.Platform.dto.Ruangan.UpdateRuanganRequest;
import com.nakahama.simpenbackend.Platform.dto.Zoom.CreateZoomRequest;
import com.nakahama.simpenbackend.Platform.dto.Zoom.UpdateZoomRequest;
import com.nakahama.simpenbackend.Platform.dto.Zoom.ZoomMapper;
import com.nakahama.simpenbackend.Platform.model.JadwalRuangan;
import com.nakahama.simpenbackend.Platform.model.JadwalZoom;
import com.nakahama.simpenbackend.Platform.model.Platform;
import com.nakahama.simpenbackend.Platform.model.Ruangan;
import com.nakahama.simpenbackend.Platform.model.Zoom;
import com.nakahama.simpenbackend.Platform.repository.PlatformDb;
import com.nakahama.simpenbackend.Platform.repository.RuanganDb;
import com.nakahama.simpenbackend.Platform.repository.ZoomDb;
import com.nakahama.simpenbackend.exception.BadRequestException;

@Service
public class PlatformServiceImpl implements PlatformService {

    @Autowired
    PlatformDb platformDb;

    @Autowired
    ZoomDb zoomDb;

    @Autowired
    RuanganDb ruanganDb;

    @Autowired
    JadwalService jadwalService;

    @Override
    public Platform save(CreateZoomRequest createZoomRequest) {
        if (getAllZoomByNama(createZoomRequest.getNama()).size() > 0)
            throw new BadRequestException("Zoom dengan nama " + createZoomRequest.getNama() + " sudah ada");
        Zoom zoom = ZoomMapper.toEntity(createZoomRequest);
        return platformDb.save(zoom);
    }

    @Override
    public Platform save(CreateRuanganRequest CreateRuanganRequest) {
        if (getAllRuanganByNamaAndCabang(CreateRuanganRequest.getNama(), CreateRuanganRequest.getCabang()).size() > 0)
            throw new BadRequestException("Ruangan dengan nama " + CreateRuanganRequest.getNama() + " pada cabang "
                    + CreateRuanganRequest.getCabang() + " sudah ada");
        Ruangan ruangan = RuanganMapper.toEntity(CreateRuanganRequest);
        return platformDb.save(ruangan);
    }

    @Override
    public List<Zoom> getAllZoom() {
        return zoomDb.findAll();
    }

    @Override
    public Platform getById(UUID id) {
        return platformDb.findById(id)
                .orElseThrow(() -> new BadRequestException("Platform dengan id " + id + " tidak ditemukan"));
    }

    @Override
    public List<Ruangan> getAllRuangan() {
        return ruanganDb.findAll();
    }

    @Override
    public List<Zoom> getAllZoomByNama(String nama) {
        return zoomDb.findByNama(nama);
    }

    @Override
    public List<Ruangan> getAllRuanganByNamaAndCabang(String nama, String cabang) {
        return ruanganDb.findByNamaAndCabang(nama, cabang);
    }

    @Override
    public Platform update(UpdateZoomRequest updateZoomRequest) {
        Zoom zoom = (Zoom) getById(updateZoomRequest.getId());
        zoom.setNama(updateZoomRequest.getNama());
        zoom.setLink(updateZoomRequest.getLink());
        return platformDb.save(zoom);
    }

    @Override
    public Platform update(UpdateRuanganRequest updateRuanganRequest) {
        Ruangan ruangan = (Ruangan) getById(updateRuanganRequest.getId());
        ruangan.setNama(updateRuanganRequest.getNama());
        ruangan.setCabang(updateRuanganRequest.getCabang());
        ruangan.setKapasitas(updateRuanganRequest.getKapasitas());
        return platformDb.save(ruangan);
    }

    @Override
    public void delete(UUID id) {
        platformDb.delete(getById(id));
    }

    @Override
    public List<JadwalZoom> assignZoom(List<SesiKelas> listSesiKelas) {

        Zoom zoom = jadwalService.findAvalaibleZoom(listSesiKelas);
        List<JadwalZoom> listJadwalZoom = new ArrayList<>();
        for (SesiKelas sesiKelas : listSesiKelas) {

            JadwalZoom jadwalZoom = new JadwalZoom();
            jadwalZoom.setWaktu(sesiKelas.getWaktuPelaksanaan());
            jadwalZoom.setZoom(zoom);
            jadwalService.create(jadwalZoom);
            jadwalZoom.setSesiKelas(sesiKelas);
            sesiKelas.setJadwalZoom(jadwalZoom);
            listJadwalZoom.add(jadwalZoom);

            jadwalService.create(jadwalZoom);

        }
        return listJadwalZoom;
    }

    @Override
    public List<JadwalRuangan> assignRuangan(List<SesiKelas> listSesiKelas) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assignRuangan'");
    }

}
