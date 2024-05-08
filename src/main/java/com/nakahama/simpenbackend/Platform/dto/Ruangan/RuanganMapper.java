package com.nakahama.simpenbackend.Platform.dto.Ruangan;

import java.util.ArrayList;
import java.util.List;

import com.nakahama.simpenbackend.Platform.model.JadwalRuangan;
import com.nakahama.simpenbackend.Platform.model.Platform;
import com.nakahama.simpenbackend.Platform.model.Ruangan;

public class RuanganMapper {
    public static Ruangan toEntity(CreateRuanganRequest request) {
        Ruangan ruangan = new Ruangan();
        ruangan.setNama(request.getNama());
        ruangan.setCabang(request.getCabang());
        ruangan.setKapasitas(request.getKapasitas());
        return ruangan;
    }

    public static ReadJadwalRuangan toReadJadwalRuangan(JadwalRuangan request) {
        ReadJadwalRuangan response = new ReadJadwalRuangan();
        response.setId(request.getId());
        response.setPlatformId(request.getRuangan().getId());
        response.setNama(request.getRuangan().getNama());
        response.setCabang(request.getRuangan().getCabang());
        response.setKapasitas(request.getRuangan().getKapasitas());
        response.setWaktu(request.getWaktu());
        response.setNamaPengajar(request.getSesiKelas().getPengajar().getNama());
        return response;
    }

    public static ReadRuangan toReadRuangan(Ruangan request) {
        ReadRuangan response = new ReadRuangan();
        response.setId(request.getId());
        response.setNama(request.getNama());
        response.setCabang(request.getCabang());
        response.setKapasitas(request.getKapasitas());
        return response;
    }

    public static List<List<ReadRuangan>> toListReadSesiRuangan(List<List<Platform>> request) {
        List<List<ReadRuangan>> response = new ArrayList<>();
        for (List<Platform> listPlatform : request) {
            List<ReadRuangan> listReadRuangan = new ArrayList<>();
            if (listPlatform.isEmpty()) {
                response.add(listReadRuangan);
                continue;
            }
            for (Platform platform : listPlatform) {
                if (platform instanceof Ruangan) {
                    ReadRuangan readRuangan = toReadRuangan((Ruangan) platform);
                    listReadRuangan.add(readRuangan);
                }
            }
            response.add(listReadRuangan);

        }
        return response;
    }

    public static ReadDetailRuangan toReadDetailRuangan(Ruangan request, List<JadwalRuangan> jadwalRuangan) {
        ReadDetailRuangan response = new ReadDetailRuangan();
        response.setId(request.getId());
        response.setNama(request.getNama());
        response.setCabang(request.getCabang());
        response.setKapasitas(request.getKapasitas());
        List<ReadJadwalRuangan> listReadJadwalRuangan = new ArrayList<>();
        for (JadwalRuangan jadwal : jadwalRuangan) {
            ReadJadwalRuangan readJadwalRuangan = toReadJadwalRuangan(jadwal);
            listReadJadwalRuangan.add(readJadwalRuangan);
        }
        response.setJadwalPemakaian(listReadJadwalRuangan);
        return response;
    }
}
