package com.nakahama.simpenbackend.Kelas.dto.JenisKelas;

import com.nakahama.simpenbackend.Kelas.model.JenisKelas;
import com.nakahama.simpenbackend.User.model.Akademik;

import java.util.List;
import java.util.ArrayList;

public class JenisKelasMapper {

    public static List<JenisKelas> toEntity(CreateJenisKelas jenisKelasRequest, Akademik picAkademik) {
        List<JenisKelas> listJenisKelas = new ArrayList<>();

        for (String pertemuanValue : jenisKelasRequest.getModaPertemuan()) {
            for (String tipeValue : jenisKelasRequest.getTipe()) {
                for (String bahasaValue : jenisKelasRequest.getBahasa()) {
                    JenisKelas jenisKelas = new JenisKelas();
                    jenisKelas.setNama(jenisKelasRequest.getNama());
                    jenisKelas.setModaPertemuan(pertemuanValue);
                    jenisKelas.setTipe(tipeValue);
                    jenisKelas.setBahasa(bahasaValue);
                    jenisKelas.setPicAkademik(picAkademik);
                    listJenisKelas.add(jenisKelas);
                }
            }
        }

        return listJenisKelas;
    }

    public static List<JenisKelas> toEntity(UpdateJenisKelas jenisKelasRequest, Akademik picAkademik) {
        List<JenisKelas> listJenisKelas = new ArrayList<>();

        for (String pertemuanValue : jenisKelasRequest.getModaPertemuan()) {
            for (String tipeValue : jenisKelasRequest.getTipe()) {
                for (String bahasaValue : jenisKelasRequest.getBahasa()) {
                    JenisKelas jenisKelas = new JenisKelas();
                    jenisKelas.setNama(jenisKelasRequest.getNama());
                    jenisKelas.setModaPertemuan(pertemuanValue);
                    jenisKelas.setTipe(tipeValue);
                    jenisKelas.setBahasa(bahasaValue);
                    jenisKelas.setPicAkademik(picAkademik);
                    listJenisKelas.add(jenisKelas);
                }
            }
        }

        return listJenisKelas;
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
        jenisKelasDTO.setPertemuan(jenisKelas.getModaPertemuan());
        jenisKelasDTO.setTipe(jenisKelas.getTipe());

        return jenisKelasDTO;
    }

}
