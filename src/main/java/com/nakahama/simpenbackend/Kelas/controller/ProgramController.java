package com.nakahama.simpenbackend.Kelas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.nakahama.simpenbackend.Kelas.model.Program;
import com.nakahama.simpenbackend.Kelas.service.ProgramService;
import com.nakahama.simpenbackend.util.BaseResponse;

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

    @PostMapping("/")
    public BaseResponse createProgram(@RequestBody Program programRequest,
            @RequestHeader("Authorization") String token) {

        Program program = programService.save(programRequest);

        // TODO: Add error handling
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setStatus("OK");
        response.setMessage("Success");
        response.setContent(program);
        return response;
    }

    @GetMapping(value = "/")
    public BaseResponse getProgram(@RequestHeader("Authorization") String token) {

        List<Program> listProgram = programService.getAll();

        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setStatus("OK");
        response.setMessage("Success");
        response.setContent(listProgram);
        return response;
    }

    @PutMapping("/")
    public BaseResponse updateProgram(@RequestBody Program programRequest, @RequestBody String entity) {

        Program program = programService.update(programRequest);

        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setStatus("OK");
        response.setMessage("Success");
        response.setContent(program);
        return response;
    }

    @DeleteMapping(value = "/")
    public BaseResponse deleteProgram(@RequestBody UUID id) {

        programService.delete(id);

        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setStatus("OK");
        response.setMessage("Success");
        return response;
    }

}
