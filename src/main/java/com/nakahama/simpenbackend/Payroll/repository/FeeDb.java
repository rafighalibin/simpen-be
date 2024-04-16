package com.nakahama.simpenbackend.Payroll.repository;

import com.nakahama.simpenbackend.Kelas.model.JenisKelas;
import com.nakahama.simpenbackend.Kelas.model.Program;
import com.nakahama.simpenbackend.Payroll.model.FeeModel;

import jakarta.transaction.Transactional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface FeeDb extends JpaRepository<FeeModel, UUID>{

    @Query("SELECT DISTINCT f.program FROM FeeModel f")
    List<Program> findDistinctProgram();

    List<FeeModel> findByProgram(Program program);

    FeeModel findByProgramAndJenisKelas(Program program, JenisKelas jenisKelas);
}
