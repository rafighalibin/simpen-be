package com.nakahama.simpenbackend.Kelas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nakahama.simpenbackend.Kelas.dto.SesiKelas.ReadDetailSesiKelas;
import com.nakahama.simpenbackend.Kelas.dto.SesiKelas.UpdateAbsensiMurid;
import com.nakahama.simpenbackend.Kelas.dto.SesiKelas.SesiKelasMapper;
import com.nakahama.simpenbackend.Kelas.service.SesiKelasService;
import com.nakahama.simpenbackend.util.ResponseUtil;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sesi")
public class SesiKelasController {

    @Autowired
    SesiKelasService sesiKelasService;

    @GetMapping("/{idKelas}")
    public ResponseEntity<Object> getSesiByKelasId(@PathVariable(value="idKelas") int idKelas) {
        List<ReadDetailSesiKelas> response = SesiKelasMapper
                .toListReadDetailDto(sesiKelasService.getByKelasId(idKelas));
        return ResponseUtil.okResponse(response, "Success");
    }

    @PutMapping("/absen/{idSesi}")
    public ResponseEntity<Object> update(@Valid @RequestBody List<UpdateAbsensiMurid> updateAbsensiMurid,
            @PathVariable String idSesi) {
        sesiKelasService.uppdateAbsenSesi(UUID.fromString(idSesi), updateAbsensiMurid);
        return ResponseUtil.okResponse(null, "Success");
    }

}
