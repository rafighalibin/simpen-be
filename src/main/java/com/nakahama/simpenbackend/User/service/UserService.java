package com.nakahama.simpenbackend.User.service;

import com.nakahama.simpenbackend.User.model.UserModel;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

public interface UserService {

    public List<UserModel> retrieveAllUser();

    public String generatePassword(int length);

    public boolean isDeactivate(String email);

    public boolean isExist(String email);

    public UserModel addUser(String email, String role, String nama);

    public UserModel getUserById(UUID id);

    public void addDummySuperadmin();

    //update user
    public UserModel updateUser(UserModel user);

}
