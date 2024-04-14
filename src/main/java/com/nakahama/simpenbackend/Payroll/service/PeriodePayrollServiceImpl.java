package com.nakahama.simpenbackend.Payroll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Payroll.model.PeriodePayroll;
import com.nakahama.simpenbackend.Payroll.repository.PeriodePayrollDb;

@Service
public class PeriodePayrollServiceImpl implements PeriodePayrollService{

    @Autowired 
    PeriodePayrollDb periodePayrollDb;

    @Override
    public PeriodePayroll getById(int id) {
        return periodePayrollDb.findById(id).get();
    }

    @Override
    public PeriodePayroll createPeriodePayroll(PeriodePayroll periodePayroll) {
        return periodePayrollDb.save(periodePayroll);
    }

    @Override
    public List<PeriodePayroll> getAllPeriodePayroll() {
        return periodePayrollDb.findAll();
    }
}
