package com.nakahama.simpenbackend.Payroll.dto.Fee;

import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.JenisKelasMapper;
import com.nakahama.simpenbackend.Kelas.model.JenisKelas;
import com.nakahama.simpenbackend.Kelas.model.Program;
import com.nakahama.simpenbackend.Payroll.model.FeeModel;

public class FeeMapper {
    public static FeeModel toEntity(CreateFee request) {
        FeeModel response = new FeeModel();
        response.setBaseFee(request.getBaseFee());
        response.setStudentMultiplier(request.getStudentMultiplier());
        response.setMaxStudents(request.getMaxStudents());
        return response;
    }

    public static ReadFee toDto(FeeModel entity) {
        ReadFee response = new ReadFee();
        response.setId(entity.getId());
        JenisKelas jenisKelas = entity.getJenisKelas();
        response.setJenisKelas(JenisKelasMapper.toReadDto(jenisKelas));
        Program program = entity.getProgram();
        response.setProgram(program.getNama());
        response.setBaseFee(entity.getBaseFee());
        response.setStudentMultiplier(entity.getStudentMultiplier());
        response.setMaxStudents(entity.getMaxStudents());
        return response;
    }
}
