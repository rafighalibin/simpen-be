package com.nakahama.simpenbackend.Kelas.dto.SesiKelas;

import java.util.*;

import com.nakahama.simpenbackend.Kelas.dto.Murid.MuridMapper;
import com.nakahama.simpenbackend.Kelas.model.SesiKelas;

public class SesiKelasMapper {

    public static SesiKelasDTO toDto(SesiKelas request) {
        SesiKelasDTO response = new SesiKelasDTO();
        response.setSesi_id(request.getId());
        response.setStatus(request.getStatus());
        response.setWaktuPelaksanaan(request.getWaktuPelaksanaan());
        return response;
    }

    public static List<ReadDetailSesiKelas> toListReadDetailDto(List<SesiKelas> request) {
        List<ReadDetailSesiKelas> response = new ArrayList<>();
        for (SesiKelas sesiKelas : request) {
            ReadDetailSesiKelas detailSesiKelas = toReadDetailDto(sesiKelas);
            detailSesiKelas.setNoSesi(response.size() + 1);
            response.add(detailSesiKelas);
        }
        return response;
    }

    private static ReadDetailSesiKelas toReadDetailDto(SesiKelas sesiKelas) {
        ReadDetailSesiKelas response = new ReadDetailSesiKelas();
        response.setSesi_id(sesiKelas.getId());
        response.setStatus(sesiKelas.getStatus());
        response.setWaktuPelaksanaan(sesiKelas.getWaktuPelaksanaan());
        response.setListMuridSesi(MuridMapper.toListReadMuridSesi(sesiKelas.getListMuridSesi()));

        return response;
    }

}
