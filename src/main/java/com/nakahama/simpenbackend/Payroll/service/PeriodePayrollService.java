package com.nakahama.simpenbackend.Payroll.service;

import java.util.List;

import com.nakahama.simpenbackend.Payroll.model.PeriodePayroll;

public interface PeriodePayrollService {
    public PeriodePayroll getById(int id);
    
    public PeriodePayroll createPeriodePayroll(PeriodePayroll periodePayroll);

    public List<PeriodePayroll> getAllPeriodePayroll();
}
