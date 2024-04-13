package com.nakahama.simpenbackend.Platform.dto.Ruangan;

import com.nakahama.simpenbackend.Platform.model.Ruangan;

public class RuanganMapper {
    public static Ruangan toEntity(CreateRuanganRequest request) {
        Ruangan ruangan = new Ruangan();
        ruangan.setNama(request.getNama());
        ruangan.setCabang(request.getCabang());
        ruangan.setKapasitas(request.getKapasitas());
        return ruangan;
    }
}
