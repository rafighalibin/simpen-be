package com.nakahama.simpenbackend.Kelas.dto.JenisKelas;

import com.nakahama.simpenbackend.Kelas.dto.Program.ProgramDTO;
import com.nakahama.simpenbackend.Kelas.dto.Program.ProgramMapper;
import com.nakahama.simpenbackend.Kelas.model.JenisKelas;
import com.nakahama.simpenbackend.Kelas.model.Kelas;
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

    public static ReadJenisKelas toReadDto(JenisKelas request) {
        ReadJenisKelas response = new ReadJenisKelas();
        response.setId(request.getId());
        response.setBahasa(request.getBahasa());
        response.setNama(request.getNama());
        response.setPertemuan(request.getModaPertemuan());
        response.setTipe(request.getTipe());
        response.setPicAkademikId(request.getPicAkademik().getId());
        response.setPicAkademikNama(request.getPicAkademik().getNama());
        if (request.getKelas() != null) {
            for (Kelas kelas : request.getKelas()) {
                if (response.getListProgram() != null){
                    response.getListProgram().add(ProgramMapper.toDto(kelas.getProgram()));
                } else{
                    response.setListProgram(new ArrayList<ProgramDTO>());
                }
            }
        }

        return response;
    }

    public static ReadJenisAddKelas toReadAddDto(ReadJenisKelas request, Boolean hasFee) {
        ReadJenisAddKelas response = new ReadJenisAddKelas();
        response.setId(request.getId());
        response.setBahasa(request.getBahasa());
        response.setNama(request.getNama());
        response.setPertemuan(request.getPertemuan());
        response.setTipe(request.getTipe());
        response.setPicAkademikId(request.getPicAkademikId());
        response.setPicAkademikNama(request.getPicAkademikNama());
        response.setListProgram(request.getListProgram());
        response.setHasFee(hasFee);
        return response;
    }

}
