package com.nakahama.simpenbackend.Auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nakahama.simpenbackend.Auth.dto.LoginReqDTO;
import com.nakahama.simpenbackend.Auth.service.AuthService;
import com.nakahama.simpenbackend.util.BaseResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Deprecated
    @PostMapping("/login")
    public ResponseEntity<?> authWithJwt(@RequestBody LoginReqDTO loginReqDTO) {
        BaseResponse response = new BaseResponse();
        try {
            if (authService.isExist(loginReqDTO)) {
                String token = authService.loginWithJwt(loginReqDTO);
                if (token == null) {
                    response.setCode(HttpStatus.UNAUTHORIZED.value());
                    response.setStatus(HttpStatus.UNAUTHORIZED.getReasonPhrase());
                    response.setMessage("Invalid credentials");
                } else {
                    token = "Bearer " + token;
                    response.setCode(HttpStatus.OK.value());
                    response.setStatus(HttpStatus.OK.getReasonPhrase());
                    response.setMessage("Login successful");
                    response.setContent(token);
                }
            } else {
                response.setCode(HttpStatus.UNAUTHORIZED.value());
                response.setStatus(HttpStatus.UNAUTHORIZED.getReasonPhrase());
                response.setMessage("Account doesn't exist");
            }
        } catch (Exception e) {
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.setMessage("Internal server error");
        }
        return ResponseEntity.status(response.getCode()).body(response);
    }

}
