package com.nakahama.simpenbackend.Auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.nakahama.simpenbackend.Auth.dto.LoginReqDTO;
import com.nakahama.simpenbackend.Auth.security.JwtUtils;
import com.nakahama.simpenbackend.User.model.UserModel;
import com.nakahama.simpenbackend.User.repository.UserDb;
import com.nakahama.simpenbackend.User.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    UserDb userDb;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @Deprecated
    public String loginWithJwt(LoginReqDTO loginReqDTO) {
        UserModel user = userDb.findByEmail(loginReqDTO.getEmail());
        if (authenticate(user, loginReqDTO.getPassword()) && !user.isDeleted()) {
            return jwtUtils.generateToken(user.getEmail(), user.getId(), String.valueOf(user.getRole()));
        } else {
            return null;
        }
    }

    public boolean authenticate(UserModel userModel, String password) {
        if (userModel.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isExist(LoginReqDTO loginReqDTO) {
        UserModel user = userDb.findByEmail(loginReqDTO.getEmail());
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }

    public String getToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        } else {
            return null;
        }
    }

    @Deprecated
    public UserModel getLoggedUser(HttpServletRequest request) {
        String token = getToken(request);
        if (token != null && jwtUtils.validateToken(token)) {
            String id = jwtUtils.getIdFromJwt(token);
            UserModel user = userService.getUserById(UUID.fromString(id));
            if (user != null) {
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
