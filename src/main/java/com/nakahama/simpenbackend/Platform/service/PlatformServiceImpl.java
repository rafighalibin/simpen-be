package com.nakahama.simpenbackend.Platform.service;

import java.util.*;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.model.SesiKelas;
import com.nakahama.simpenbackend.Kelas.service.SesiKelasService;
import com.nakahama.simpenbackend.Platform.dto.Ruangan.CreateRuanganRequest;
import com.nakahama.simpenbackend.Platform.dto.Ruangan.ReadDetailRuangan;
import com.nakahama.simpenbackend.Platform.dto.Ruangan.ReadRuangan;
import com.nakahama.simpenbackend.Platform.dto.Ruangan.RuanganMapper;
import com.nakahama.simpenbackend.Platform.dto.Ruangan.UpdateRuanganRequest;
import com.nakahama.simpenbackend.Platform.dto.Zoom.CreateZoomRequest;
import com.nakahama.simpenbackend.Platform.dto.Zoom.ReadDetailZoom;
import com.nakahama.simpenbackend.Platform.dto.Zoom.ReadZoom;
import com.nakahama.simpenbackend.Platform.dto.Zoom.UpdateZoomRequest;
import com.nakahama.simpenbackend.Platform.dto.Zoom.ZoomMapper;
import com.nakahama.simpenbackend.Platform.model.JadwalRuangan;
import com.nakahama.simpenbackend.Platform.model.JadwalZoom;
import com.nakahama.simpenbackend.Platform.model.Platform;
import com.nakahama.simpenbackend.Platform.model.Ruangan;
import com.nakahama.simpenbackend.Platform.model.Zoom;
import com.nakahama.simpenbackend.Platform.repository.JadwalRuanganDb;
import com.nakahama.simpenbackend.Platform.repository.JadwalZoomDb;
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
    JadwalZoomDb jadwalZoomDb;

    @Autowired
    JadwalRuanganDb jadwalRuanganDb;

    @Autowired
    RuanganDb ruanganDb;

    @Autowired
    JadwalService jadwalService;

    @Autowired
    SesiKelasService sesiKelasService;

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
    public List<ReadZoom> getAllZoom() {
        List<Zoom> listZoom = zoomDb.findAll();
        List<ReadZoom> listReadZoom = new ArrayList<>();
        for (Zoom zoom : listZoom) {
            listReadZoom.add(ZoomMapper.toReadZoom(zoom));
        }
        return listReadZoom;
    }

    @Override
    public Platform getById(UUID id) {
        return platformDb.findById(id)
                .orElseThrow(() -> new BadRequestException("Platform dengan id " + id + " tidak ditemukan"));
    }

    @Override
    public List<ReadRuangan> getAllRuangan() {
        List<Ruangan> listRuangan = ruanganDb.findAll();
        List<ReadRuangan> listReadRuangan = new ArrayList<>();
        for (Ruangan ruangan : listRuangan) {
            listReadRuangan.add(RuanganMapper.toReadRuangan(ruangan));
        }
        return listReadRuangan;
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
        Platform platform = getById(id);
        if (platform instanceof Zoom) {
            List<JadwalZoom> jadwalZoom = jadwalZoomDb.findByZoom((Zoom) platform);
            if(jadwalZoom.size() == 0){
                platformDb.delete(getById(id));
                return;
            }
            List<LocalDateTime> listWaktu = new ArrayList<>();
            for (JadwalZoom jadwal : jadwalZoom) {
                listWaktu.add(jadwal.getWaktu());
            }
            Zoom zoomChanges = jadwalService.findAvailableZoomByDateTime(listWaktu);
            for (JadwalZoom jadwal : jadwalZoom) {
                jadwal.setZoom(zoomChanges);
                jadwalZoomDb.save(jadwal);
                jadwalZoomDb.flush();
            }
        } else{
            List<JadwalRuangan> jadwalRuangan = jadwalRuanganDb.findByRuangan((Ruangan) platform);
            if(jadwalRuangan.size() == 0){
                platformDb.delete(getById(id));
                return;
            }
            List<LocalDateTime> listWaktu = new ArrayList<>();
            for (JadwalRuangan jadwal : jadwalRuangan) {
                listWaktu.add(jadwal.getWaktu());
            }
            Ruangan ruanganChanges = jadwalService.findAvailableRuanganByDateTime(listWaktu, ((Ruangan) platform).getCabang());
            for (JadwalRuangan jadwal : jadwalRuangan) {
                jadwal.setRuangan(ruanganChanges);
                jadwalRuanganDb.save(jadwal);
                jadwalRuanganDb.flush();
            }
        }
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
            jadwalService.save(jadwalZoom);
            jadwalZoom.setSesiKelas(sesiKelas);
            sesiKelas.setJadwalZoom(jadwalZoom);
            listJadwalZoom.add(jadwalZoom);

            jadwalService.save(jadwalZoom);
            sesiKelasService.updateJadwal(sesiKelas);
        }
        return listJadwalZoom;
    }

    @Override
    public List<JadwalZoom> assignZoom(List<SesiKelas> listSesiKelas, String idZoom) {
        Zoom zoom = zoomDb.findById(UUID.fromString(idZoom)).get();
        List<JadwalZoom> listJadwalZoom = new ArrayList<>();
        for (SesiKelas sesiKelas : listSesiKelas) {

            JadwalZoom jadwalZoom = new JadwalZoom();
            jadwalZoom.setWaktu(sesiKelas.getWaktuPelaksanaan());
            jadwalZoom.setZoom(zoom);
            jadwalService.save(jadwalZoom);
            jadwalZoom.setSesiKelas(sesiKelas);
            sesiKelas.setJadwalZoom(jadwalZoom);
            listJadwalZoom.add(jadwalZoom);

            jadwalService.save(jadwalZoom);
            sesiKelasService.updateJadwal(sesiKelas);
        }
        return listJadwalZoom;
    }

    @Override
    public List<JadwalRuangan> assignRuangan(List<SesiKelas> listSesiKelas, String idRuangan) {
        Ruangan ruangan = ruanganDb.findById(UUID.fromString(idRuangan)).get();
        List<JadwalRuangan> listJadwalRuangan = new ArrayList<>();
        for (SesiKelas sesiKelas : listSesiKelas) {

            JadwalRuangan jadwalRuangan = new JadwalRuangan();
            jadwalRuangan.setWaktu(sesiKelas.getWaktuPelaksanaan());
            jadwalRuangan.setRuangan(ruangan);
            jadwalService.save(jadwalRuangan);
            jadwalRuangan.setSesiKelas(sesiKelas);
            sesiKelas.setJadwalRuangan(jadwalRuangan);
            listJadwalRuangan.add(jadwalRuangan);

            jadwalService.save(jadwalRuangan);
            sesiKelasService.updateJadwal(sesiKelas);
        }
        return listJadwalRuangan;
    }

    @Override
    public List<String> getDistinctCabang() {
        return ruanganDb.findDistinctCabang();
    }

    @Override
    public List<Platform> getByCabang(String cabang) {
        return ruanganDb.findByCabang(cabang);
    }

    @Override
    public ReadDetailZoom getDetailZoom(UUID id) {
        Zoom zoom = (Zoom) getById(id);
        List<JadwalZoom> jadwalZoom = jadwalZoomDb.findByZoom(zoom);
        return ZoomMapper.toReadDetailZoom(zoom, jadwalZoom);
    }

    @Override
    public ReadDetailRuangan getDetailRuangan(UUID id){
        Ruangan ruangan = (Ruangan) getById(id);
        List<JadwalRuangan> jadwalRuangan = jadwalRuanganDb.findByRuangan(ruangan);
        return RuanganMapper.toReadDetailRuangan(ruangan, jadwalRuangan);
    }
}
