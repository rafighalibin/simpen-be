package com.nakahama.simpenbackend.Payroll.dto;

import com.nakahama.simpenbackend.Payroll.model.AbsenPengajar;

public class AbsenPengajarMapper {
    public static ReadAbsenPengajar toReadDto(AbsenPengajar absenPengajar) {
        ReadAbsenPengajar response = new ReadAbsenPengajar();
        response.setId(absenPengajar.getKelas().getKelasId());
        response.setPengajar(absenPengajar.getPengajar().getNama());
        response.setJenisKelasName(absenPengajar.getKelas().getJenisKelas().getNama());
        response.setProgramName(absenPengajar.getKelas().getProgram().getNama());
        response.setFee(absenPengajar.getJumlahFee());

        return response;
    }
}
