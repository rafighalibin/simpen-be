package com.nakahama.simpenbackend.User.service;

import com.nakahama.simpenbackend.User.dto.User.EditUserRequestDTO;
import com.nakahama.simpenbackend.User.model.UserModel;
import java.util.List;
import java.util.UUID;
import java.util.Map;

public interface UserService {

    public List<UserModel> retrieveAllUser();

    public String generatePassword(int length);

    public boolean isDeactivate(String email);

    public boolean isExistByEmail(String email);

    public UserModel addUser(String email, String role, String nama);

    public UserModel getUserById(UUID id);

    public void addDummySuperadmin();

    public Map<String, List<UserModel>> getAllUsersGroupedByCategory();

    public void deleteUser(UUID id);

    public UserModel updateUser(EditUserRequestDTO editUserRequestDTO);

}
