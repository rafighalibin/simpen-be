package com.nakahama.simpenbackend.User.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nakahama.simpenbackend.User.dto.AddUserRequestDTO;
import com.nakahama.simpenbackend.User.model.UserModel;
import com.nakahama.simpenbackend.User.service.UserServiceImpl;
import com.nakahama.simpenbackend.util.BaseResponse;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/user")
    public ResponseEntity<?> AddUser(@RequestBody AddUserRequestDTO addUserRequestDTO) {
        BaseResponse response = new BaseResponse();
        try {
            UserModel newUser = userService.addUser(addUserRequestDTO.getEmail(), addUserRequestDTO.getRole(),
                    addUserRequestDTO.getNama());

            if (newUser != null) {
                response.setCode(HttpStatus.OK.value());
                response.setStatus(HttpStatus.OK.getReasonPhrase());
                response.setMessage("User successfully added");
                response.setContent(newUser);
            } else {
                response.setCode(HttpStatus.OK.value());
                response.setStatus(HttpStatus.OK.getReasonPhrase());
                response.setMessage("User already exist");
                response.setContent(newUser);
            }
        } catch (Exception e) {
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.setMessage("Internal server error: " + e.getMessage());
        }

        return ResponseEntity.status(response.getCode()).body(response);

    }

}
