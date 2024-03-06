package com.nakahama.simpenbackend.Kelas.dto.SesiKelas;

import com.nakahama.simpenbackend.Kelas.model.SesiKelas;

public class SesiKelasMapper {

    public static SesiKelasDTO toDto(SesiKelas sesiKelas) {
        SesiKelasDTO sesiKelasDTO = new SesiKelasDTO();
        sesiKelasDTO.setSesi_id(sesiKelas.getId());
        sesiKelasDTO.setStatus(sesiKelas.getStatus());
        sesiKelasDTO.setWaktuPelaksanaan(sesiKelas.getWaktuPelaksanaan());
        return sesiKelasDTO;
    }

}
