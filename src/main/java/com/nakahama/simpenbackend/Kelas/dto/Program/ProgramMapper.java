package com.nakahama.simpenbackend.Kelas.dto.Program;

import java.util.ArrayList;

import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.JenisKelasDTO;
import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.JenisKelasMapper;
import com.nakahama.simpenbackend.Kelas.model.Kelas;
import com.nakahama.simpenbackend.Kelas.model.Program;

public class ProgramMapper {

    public static ProgramDTO toDto(Program request) {
        ProgramDTO response = new ProgramDTO();
        response.setId(request.getId());
        response.setNama(request.getNama());
        response.setJumlahLevel(request.getJumlahLevel());
        response.setJumlahPertemuan(request.getJumlahPertemuan());
        return response;
    }

    public static ReadProgram toReadDto(Program request) {
        ReadProgram response = new ReadProgram();
        response.setId(request.getId());
        response.setNama(request.getNama());
        response.setJumlahLevel(request.getJumlahLevel());
        response.setJumlahPertemuan(request.getJumlahPertemuan());
        response.setListJenisKelas(new ArrayList<JenisKelasDTO>());
        for (Kelas kelas : request.getKelas()) {
            response.getListJenisKelas().add(JenisKelasMapper.toDto(kelas.getJenisKelas()));
        }
        return response;
    }

    public static Program toEntity(CreateProgram request) {
        Program response = new Program();
        response.setNama(request.getNama());
        response.setJumlahLevel(request.getJumlahLevel());
        response.setJumlahPertemuan(request.getJumlahPertemuan());

        return response;
    }

}
