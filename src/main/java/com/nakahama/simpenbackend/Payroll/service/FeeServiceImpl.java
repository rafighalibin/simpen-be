package com.nakahama.simpenbackend.Payroll.service;

import java.util.*;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Kelas.dto.Program.ReadProgram;
import com.nakahama.simpenbackend.Kelas.dto.Program.ProgramMapper;
import com.nakahama.simpenbackend.Kelas.model.JenisKelas;
import com.nakahama.simpenbackend.Kelas.model.Kelas;
import com.nakahama.simpenbackend.Kelas.model.Program;
import com.nakahama.simpenbackend.Kelas.service.JenisKelasService;
import com.nakahama.simpenbackend.Kelas.service.ProgramService;
import com.nakahama.simpenbackend.Payroll.dto.Fee.CreateFee;
import com.nakahama.simpenbackend.Payroll.dto.Fee.FeeMapper;
import com.nakahama.simpenbackend.Payroll.dto.Fee.ReadFee;
import com.nakahama.simpenbackend.Payroll.dto.Fee.ReadFeeGrouped;
import com.nakahama.simpenbackend.Payroll.dto.Fee.UpdateFee;
import com.nakahama.simpenbackend.Payroll.model.FeeModel;
import com.nakahama.simpenbackend.Payroll.repository.FeeDb;
import com.nakahama.simpenbackend.exception.BadRequestException;

@Service
public class FeeServiceImpl implements FeeService {

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
        if (!program.getJenisKelas().contains(jenisKelas)) {
            throw new BadRequestException("Program with id " + feeRequest.getProgram()
                    + " does not have jenis kelas with id " + feeRequest.getJenisKelas());
        }
        if (feeDb.findByProgramAndJenisKelas(program, jenisKelas) != null) {
            throw new BadRequestException("Fee for program with id " + feeRequest.getProgram()
                    + " and jenis kelas with id " + feeRequest.getJenisKelas() + " already exists");
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
            if (!(kelas.getStatus().equals("Finished"))) {
                if (kelas.getProgram().getId().equals(fee.getProgram().getId())) {
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

    @Override
    public List<ReadProgram> getDistinctProgram() {
        List<Program> listProgramDistinct = feeDb.findDistinctProgram();
        if (listProgramDistinct.isEmpty()) {
            throw new BadRequestException("No program found");
        }

        List<ReadProgram> listProgram = new ArrayList<ReadProgram>();

        for (Program program : listProgramDistinct) {
            ReadProgram response = ProgramMapper.toReadDto(program);
            listProgram.add(response);
        }
        return listProgram;
    }

    @Override
    public List<ReadFeeGrouped> getFeeGrouped() {
        List<ReadFeeGrouped> listFeeGrouped = new ArrayList<ReadFeeGrouped>();
        List<Program> listProgramDistinct = feeDb.findDistinctProgram();
        if (listProgramDistinct.isEmpty()) {
            return listFeeGrouped;
        }

        for (Program program : listProgramDistinct) {
            ReadFeeGrouped response = new ReadFeeGrouped();
            response.setProgram(ProgramMapper.toReadDto(program));
            response.setListFee(new ArrayList<ReadFee>());
            for (FeeModel fee : feeDb.findByProgram(program)) {
                ReadFee feeResponse = FeeMapper.toDto(fee);
                response.getListFee().add(feeResponse);
            }
            listFeeGrouped.add(response);
        }
        return listFeeGrouped;
    }

    @Override
    public FeeModel getByProgramAndJenisKelas(UUID programId, UUID jenisKelasId) {
        Program program = programService.getById(programId);
        if (program == null) {
            throw new BadRequestException("Program with id " + programId + " not found");
        }
        JenisKelas jenisKelas = jenisKelasService.getById(jenisKelasId);
        if (jenisKelas == null) {
            throw new BadRequestException("Jenis Kelas with id " + jenisKelasId + " not found");
        }
        FeeModel fee = feeDb.findByProgramAndJenisKelas(program, jenisKelas);
        if (fee == null) {
            throw new BadRequestException(
                    "Fee for program with id " + programId + " and jenis kelas with id " + jenisKelasId + " not found");
        }
        return fee;
    }
}
