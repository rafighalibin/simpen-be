package com.nakahama.simpenbackend.Kelas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nakahama.simpenbackend.Kelas.dto.Murid.CreateMurid;
import com.nakahama.simpenbackend.Kelas.dto.Murid.MuridMapper;
import com.nakahama.simpenbackend.Kelas.dto.Murid.ReadDetailMurid;
import com.nakahama.simpenbackend.Kelas.dto.Murid.ReadMurid;
import com.nakahama.simpenbackend.Kelas.dto.Murid.UpdateMurid;
import com.nakahama.simpenbackend.Kelas.model.Murid;
import com.nakahama.simpenbackend.Kelas.service.MuridService;
import com.nakahama.simpenbackend.util.ResponseUtil;

import java.util.List;
import java.util.ArrayList;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/murid")
public class MuridController {

    @Autowired
    MuridService muridService;

    @GetMapping("")
    public ResponseEntity<Object> getAllMurid() {
        List<ReadMurid> listMurid = new ArrayList<ReadMurid>();
        for (Murid murid : muridService.getAll())
            listMurid.add(MuridMapper.toReadDto(murid));
        return ResponseUtil.okResponse(listMurid, "Success");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMurid(@PathVariable int id) {
        ReadDetailMurid response = MuridMapper.toReadDetailDto(muridService.getById(id));
        return ResponseUtil.okResponse(response, "Success");
    }

    @PostMapping("")
    public ResponseEntity<Object> addMurid(@Valid @RequestBody CreateMurid muridRequest) {
        Murid murid = muridService.save(muridRequest);
        return ResponseUtil.okResponse(MuridMapper.toReadDetailDto(murid), "Success");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMurid(@PathVariable int id, @Valid @RequestBody UpdateMurid muridRequest) {
        muridRequest.setId(id);
        Murid murid = muridService.update(muridRequest);
        return ResponseUtil.okResponse(MuridMapper.toReadDetailDto(murid), "Success");
    }

}
