package com.nakahama.simpenbackend.Payroll.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Payroll.dto.Fee.CreateFee;
import com.nakahama.simpenbackend.Payroll.dto.Fee.ReadFee;
import com.nakahama.simpenbackend.Payroll.dto.Fee.UpdateFee;
import com.nakahama.simpenbackend.Payroll.model.FeeModel;

@Service
public interface FeeService {
    public List<ReadFee> getAll();

    public FeeModel save(CreateFee feeRequest);

    public FeeModel getById(UUID id);

    public void delete(UUID id);

    public FeeModel update(UpdateFee feeRequest);
}
