package com.nakahama.simpenbackend.User.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nakahama.simpenbackend.User.dto.request.AddUserRequestDTO;
import com.nakahama.simpenbackend.User.dto.request.EditUserRequestDTO;
import com.nakahama.simpenbackend.User.dto.response.UserGroupedResponseDTO;
import com.nakahama.simpenbackend.User.model.UserModel;
import com.nakahama.simpenbackend.User.repository.UserDb;
import com.nakahama.simpenbackend.User.service.UserServiceImpl;
import com.nakahama.simpenbackend.util.BaseResponse;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserDb userDb;

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
                response.setCode(HttpStatus.CONFLICT.value());
                response.setStatus(HttpStatus.CONFLICT.getReasonPhrase());
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

    @GetMapping("/user")
    public ResponseEntity<BaseResponse> getAllUser() {
        BaseResponse response = new BaseResponse();
        try {
            UserGroupedResponseDTO content = new UserGroupedResponseDTO();
            Map<String, List<UserModel>> listUser = userService.getAllUsersGroupedByCategory();
            if (listUser != null) {
                content.setRoles(listUser.entrySet().stream()
                        .map((Map.Entry<String, List<UserModel>> entry) -> new UserGroupedResponseDTO.CategoryUsers(
                                entry.getKey(), entry.getValue()))
                        .collect(Collectors.toList()));

                response.setCode(HttpStatus.OK.value());
                response.setStatus(HttpStatus.OK.getReasonPhrase());
                response.setMessage("Users grouped by role");
                response.setContent(listUser);
            } else {
                response.setCode(HttpStatus.NOT_FOUND.value());
                response.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
                response.setMessage("No users found");
            }
        } catch (Exception e) {
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.setMessage("Internal server error: " + e.getMessage());
        }
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        BaseResponse response = new BaseResponse();
        try {
            if (userService.getUserById(UUID.fromString(id)) != null) {
                userService.deleteUser(UUID.fromString(id));
                response.setCode(HttpStatus.OK.value());
                response.setStatus(HttpStatus.OK.getReasonPhrase());
                response.setMessage("User deleted");
            } else {
                response.setCode(HttpStatus.NOT_FOUND.value());
                response.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
                response.setMessage("No users found");
            }
        } catch (Exception e) {
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.setMessage("Internal server error: " + e.getMessage());
        }
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> editUser(@PathVariable String id, @RequestBody EditUserRequestDTO editUserRequestDTO) {
        BaseResponse response = new BaseResponse();
        try {
            editUserRequestDTO.setId(UUID.fromString(id));
            if (userService.updateUser(editUserRequestDTO) != null) {
                UserModel user = userService.getUserById(UUID.fromString(id));
                response.setCode(HttpStatus.OK.value());
                response.setStatus(HttpStatus.OK.getReasonPhrase());
                response.setMessage("User updated");
                response.setContent(user);
            } else {
                response.setCode(HttpStatus.NOT_FOUND.value());
                response.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
                response.setMessage("No users found");
            }
        } catch (Exception e) {
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.setMessage("Internal server error: " + e.getMessage());
        }

        return ResponseEntity.status(response.getCode()).body(response);

    }

}
