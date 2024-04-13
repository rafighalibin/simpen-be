package com.nakahama.simpenbackend.Payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nakahama.simpenbackend.Payroll.dto.Fee.CreateFee;
import com.nakahama.simpenbackend.Payroll.dto.Fee.ReadFee;
import com.nakahama.simpenbackend.Payroll.dto.Fee.ReadFeeGrouped;
import com.nakahama.simpenbackend.Payroll.dto.Fee.UpdateFee;
import com.nakahama.simpenbackend.Payroll.model.FeeModel;
import com.nakahama.simpenbackend.Payroll.service.FeeService;
import com.nakahama.simpenbackend.Kelas.dto.Program.ReadProgram;
import com.nakahama.simpenbackend.util.ResponseUtil;

import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/payroll/fee")
public class FeeController {
    @Autowired
    FeeService feeService;

    @PostMapping("")
    public ResponseEntity<Object> createFee(@Valid @RequestBody CreateFee feeRequest,
            @RequestHeader("Authorization") String token) {
        FeeModel fee = feeService.save(feeRequest);
        return ResponseUtil.okResponse(fee, "Fee with id " + fee.getId() + " has been created");
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> getFee(@RequestHeader("Authorization") String token) {
        List<ReadFee> listFee = feeService.getAll();
        return ResponseUtil.okResponse(listFee, "Success");
    }

    @PutMapping(value = "")
    public ResponseEntity<Object> updateFee(@Valid @RequestBody UpdateFee feeRequest,
            @RequestHeader("Authorization") String token){
        FeeModel fee = feeService.update(feeRequest);
        return ResponseUtil.okResponse(fee, "Fee with id " + fee.getId() + " has been updated");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteFee(@RequestHeader("Authorization") String token, @PathVariable("id") String id){
        feeService.delete(UUID.fromString(id));
        return ResponseUtil.okResponse(null, "Fee with id " + id + " has been deleted");
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getFeeById(@RequestHeader("Authorization") String token, @PathVariable("id") String id){
        FeeModel fee = feeService.getById(UUID.fromString(id));
        return ResponseUtil.okResponse(fee, "Success");
    }

    @GetMapping(value = "/distinct-program")
    public ResponseEntity<Object> getDistinctProgram(@RequestHeader("Authorization") String token){
        List<ReadProgram> listProgram = feeService.getDistinctProgram();
        return ResponseUtil.okResponse(listProgram, "Success");
    }

    @GetMapping(value = "/grouped")
    public ResponseEntity<Object> getFeeGrouped(@RequestHeader("Authorization") String token){
        List<ReadFeeGrouped> listFeeGrouped = feeService.getFeeGrouped();
        return ResponseUtil.okResponse(listFeeGrouped, "Success");
    }
}
