package com.nakahama.simpenbackend.Kelas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.nakahama.simpenbackend.Kelas.dto.Program.CreateProgram;
import com.nakahama.simpenbackend.Kelas.dto.Program.DeleteProgram;
import com.nakahama.simpenbackend.Kelas.dto.Program.ReadDistinctJenisKelasProgram;
import com.nakahama.simpenbackend.Kelas.dto.Program.ReadProgram;
import com.nakahama.simpenbackend.Kelas.dto.Program.UpdateProgram;
import com.nakahama.simpenbackend.Kelas.model.Program;
import com.nakahama.simpenbackend.Kelas.service.ProgramService;
import com.nakahama.simpenbackend.util.ResponseUtil;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/kelas/program")
public class ProgramController {

    @Autowired
    ProgramService programService;

    @PostMapping("")
    public ResponseEntity<Object> createProgram(@Valid @RequestBody CreateProgram programRequest,
            @RequestHeader("Authorization") String token) {
        Program program = programService.save(programRequest);
        return ResponseUtil.okResponse(program, "Program with name " + program.getNama() + " has been created");
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> getProgram(@RequestHeader("Authorization") String token) {
        List<ReadProgram> listProgram = programService.getAll();
        return ResponseUtil.okResponse(listProgram, "Success");
    }

    @PutMapping("")
    public ResponseEntity<Object> updateProgram(@Valid @RequestBody UpdateProgram programRequest) {
        ReadProgram program = programService.update(programRequest);
        return ResponseUtil.okResponse(program, "Program with name " + program.getNama() + " has been updated");
    }

    @DeleteMapping(value = "")
    public ResponseEntity<Object> deleteProgram(@Valid @RequestBody DeleteProgram programRequest) {
        programService.delete(programRequest);
        return ResponseUtil.okResponse(null, "Program with id " + programRequest.getId() + " has been deleted");
    }

    @GetMapping(value = "{id}/jenis-kelas")
    public ResponseEntity<Object> getJenisKelasByProgramId(@RequestHeader("Authorization") String token, @PathVariable(value="id") UUID id) {
        if(programService.getById(id) == null) {
            return ResponseUtil.okResponse(new ReadDistinctJenisKelasProgram(), "Program with id " + id + " not found");
        }
        List<ReadDistinctJenisKelasProgram> listProgram = programService.getDistinctJenisKelas(id);
        return ResponseUtil.okResponse(listProgram, "Success");
    }

}
