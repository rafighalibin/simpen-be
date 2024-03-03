package com.nakahama.simpenbackend.Kelas.dto.JenisKelas;

import com.nakahama.simpenbackend.Kelas.model.JenisKelas;
import com.nakahama.simpenbackend.User.model.UserModel;

public class JenisKelasMapper {

    public static JenisKelas toEntity(CreateJenisKelas jenisKelasRequest, UserModel picAkademik) {
        JenisKelas jenisKelas = new JenisKelas();
        jenisKelas.setNama(jenisKelasRequest.getNama());
        jenisKelas.setPertemuan(jenisKelasRequest.getPertemuan());
        jenisKelas.setTipe(jenisKelasRequest.getTipe());
        jenisKelas.setBahasa(jenisKelasRequest.getBahasa());
        jenisKelas.setPicAkademik(picAkademik);
        return jenisKelas;
    }

    public static JenisKelasDTO toDto(JenisKelas jenisKelas) {
        JenisKelasDTO jenisKelasDTO = new JenisKelasDTO();
        jenisKelasDTO.setId(jenisKelas.getId());
        jenisKelasDTO.setNama(jenisKelas.getNama());
        return jenisKelasDTO;
    }

    public static ReadJenisKelas toReadDto(JenisKelas jenisKelas) {
        ReadJenisKelas jenisKelasDTO = new ReadJenisKelas();
        jenisKelasDTO.setId(jenisKelas.getId());
        jenisKelasDTO.setBahasa(jenisKelas.getBahasa());
        jenisKelasDTO.setNama(jenisKelas.getNama());
        jenisKelasDTO.setPertemuan(jenisKelas.getPertemuan());
        jenisKelasDTO.setTipe(jenisKelas.getTipe());

        return jenisKelasDTO;
    }

}
