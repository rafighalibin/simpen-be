package com.nakahama.simpenbackend.Payroll.service;

import java.util.*;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.model.JenisKelas;
import com.nakahama.simpenbackend.Kelas.model.Kelas;
import com.nakahama.simpenbackend.Kelas.model.Program;
import com.nakahama.simpenbackend.Kelas.service.JenisKelasService;
import com.nakahama.simpenbackend.Kelas.service.ProgramService;
import com.nakahama.simpenbackend.Payroll.dto.Fee.CreateFee;
import com.nakahama.simpenbackend.Payroll.dto.Fee.FeeMapper;
import com.nakahama.simpenbackend.Payroll.dto.Fee.ReadFee;
import com.nakahama.simpenbackend.Payroll.dto.Fee.UpdateFee;
import com.nakahama.simpenbackend.Payroll.model.FeeModel;
import com.nakahama.simpenbackend.Payroll.repository.FeeDb;
import com.nakahama.simpenbackend.exception.BadRequestException;

@Service
public class FeeServiceImpl implements FeeService{

    @Autowired
    FeeDb feeDb;

    @Autowired
    JenisKelasService jenisKelasService;

    @Autowired
    ProgramService programService;
    
    @Override
    public List<ReadFee> getAll() {
        List<ReadFee> listFee = new ArrayList<ReadFee>();
        for (FeeModel fee : feeDb.findAll()) {
            ReadFee response = FeeMapper.toDto(fee);
            listFee.add(response);
        }
        return listFee;
    }

    @Override
    public FeeModel save(CreateFee feeRequest) {
        FeeModel fee = FeeMapper.toEntity(feeRequest);
        JenisKelas jenisKelas = jenisKelasService.getById(feeRequest.getJenisKelas());
        if (jenisKelas == null) {
            throw new BadRequestException("Jenis Kelas with id " + feeRequest.getJenisKelas() + " not found");
        }
        fee.setJenisKelas(jenisKelas);
        Program program = programService.getById(feeRequest.getProgram());
        if (program == null) {
            throw new BadRequestException("Program with id " + feeRequest.getProgram() + " not found");
        }
        fee.setProgram(program);
        fee.setLastUpdated(LocalDateTime.now());
        return feeDb.save(fee);
    }

    @Override
    public FeeModel getById(UUID id) {
        Optional<FeeModel> fee = feeDb.findById(id);
        if (fee.isPresent()) {
            return fee.get();
        }
        return null;
    }

    @Override
    public void delete(UUID id) {
        FeeModel fee = getById(id);
        List<Kelas> kelasList = fee.getJenisKelas().getKelas();
        for (Kelas kelas : kelasList) {
            if(!(kelas.getStatus().equals("Finished"))){
                if(kelas.getProgram().getId().equals(fee.getProgram().getId())){
                    throw new BadRequestException("Fee with id " + id + " is still being used in a class");
                }
            }
        }
        feeDb.deleteById(id);
    }

    @Override
    public FeeModel update(UpdateFee feeRequest) {
        FeeModel fee = getById(feeRequest.getId());
        if (fee == null) {
            throw new BadRequestException("Fee with id " + feeRequest.getId() + " not found");
        }
        fee.setBaseFee(feeRequest.getBaseFee());
        fee.setStudentMultiplier(feeRequest.getStudentMultiplier());
        fee.setMaxStudents(feeRequest.getMaxStudents());
        fee.setLastUpdated(LocalDateTime.now());
        return feeDb.save(fee);
    }
}
