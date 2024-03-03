package com.nakahama.simpenbackend.Kelas.dto.Program;

import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.JenisKelasMapper;
import com.nakahama.simpenbackend.Kelas.model.Kelas;
import com.nakahama.simpenbackend.Kelas.model.Program;

public class ProgramMapper {

    public static ReadProgram toDto(Program request) {
        ReadProgram response = new ReadProgram();
        response.setId(request.getId());
        response.setNama(request.getNama());
        response.setJumlahLevel(request.getJumlahLevel());
        response.setJumlahPertemuan(request.getJumlahPertemuan());
        for (Kelas kelas : request.getKelas()) {
            response.getJenisKelas().add(JenisKelasMapper.toDto(kelas.getJenisKelas()));
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
