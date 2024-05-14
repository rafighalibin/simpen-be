package com.nakahama.simpenbackend.Auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.nakahama.simpenbackend.Auth.dto.LoginReqDTO;
import com.nakahama.simpenbackend.Auth.security.JwtUtils;
import com.nakahama.simpenbackend.Auth.service.AuthService;
import com.nakahama.simpenbackend.Notification.model.Notification;
import com.nakahama.simpenbackend.User.model.UserModel;
import com.nakahama.simpenbackend.User.repository.UserDb;
import com.nakahama.simpenbackend.User.service.UserService;
import com.nakahama.simpenbackend.util.BaseResponse;
import com.nakahama.simpenbackend.util.ResponseUtil;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserDb userDb;

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
            response.setMessage("Internal server error: " + e.getMessage());
        }
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Deprecated
    @GetMapping("/login")
    public ResponseEntity<?> getActiveUser(HttpServletRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            UserModel user = authService.getLoggedUser(request);
            if (user != null) {

                // handle notif if expired
                List<Notification> listNotif = user.getNotifikasi();
                List<Notification> newNotif = new ArrayList<>();

                for (Notification notif : listNotif) {
                    LocalDateTime currDateTime = LocalDateTime.now();
                    if (currDateTime.isAfter(notif.getExpirationDate())) {
                        continue;
                    } else {
                        newNotif.add(notif);
                    }
                }

                // handle login session
                user.setLoggedIn(true);

                user.setNotifikasi(newNotif);
                userDb.save(user);

                response.setCode(HttpStatus.OK.value());
                response.setStatus(HttpStatus.OK.getReasonPhrase());
                response.setMessage("User found");
                response.setContent(user);
            } else {
                response.setCode(HttpStatus.NOT_FOUND.value());
                response.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
                response.setMessage("User not found");
            }
        } catch (Exception e) {
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.setMessage("Internal server error: " + e.getMessage());
        }
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Deprecated
    @PutMapping("/logout")
    public ResponseEntity<Object> userLogout(HttpServletRequest request) {
        UserModel user = authService.getLoggedUser(request);
        user.setLoggedIn(false);
        userDb.save(user);
        return ResponseUtil.okResponse(null,
                "User " + user.getNama() + " has been logged out");
    }

    @PostMapping("/test")
    public ResponseEntity<?> testAuthorized() {
        BaseResponse response = new BaseResponse();
        response.setCode(HttpStatus.OK.value());
        response.setStatus(HttpStatus.OK.getReasonPhrase());
        response.setMessage("Authorized");
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PostMapping("/test-role")
    public ResponseEntity<?> testRole() {
        BaseResponse response = new BaseResponse();
        response.setCode(HttpStatus.OK.value());
        response.setStatus(HttpStatus.OK.getReasonPhrase());
        response.setMessage("Role Detected");
        return ResponseEntity.status(response.getCode()).body(response);
    }

}
