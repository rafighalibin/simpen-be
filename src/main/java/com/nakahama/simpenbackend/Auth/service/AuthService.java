package com.nakahama.simpenbackend.Auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Auth.dto.LoginReqDTO;
import com.nakahama.simpenbackend.Auth.security.JwtUtils;
import com.nakahama.simpenbackend.User.model.UserModel;
import com.nakahama.simpenbackend.User.repository.UserDb;

@Service
public class AuthService {

    @Autowired
    UserDb userDb;

    @Autowired
    JwtUtils jwtUtils;

    @Deprecated
    public String loginWithJwt(LoginReqDTO loginReqDTO) {
        UserModel user = userDb.findByEmail(loginReqDTO.getEmail());
        if (authenticate(user, loginReqDTO.getPassword()) && !user.isDeleted()) {
            return jwtUtils.generateToken(user.getEmail(), user.getId(), user.getRole().getRole());
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
}
