package com.nakahama.simpenbackend.User.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nakahama.simpenbackend.Auth.service.AuthService;
import com.nakahama.simpenbackend.User.dto.User.AddUserRequestDTO;
import com.nakahama.simpenbackend.User.dto.User.EditDataPengajarRequestDTO;
import com.nakahama.simpenbackend.User.dto.User.EditUserRequestDTO;
import com.nakahama.simpenbackend.User.model.Pengajar;
import com.nakahama.simpenbackend.User.model.UserModel;
import com.nakahama.simpenbackend.User.repository.UserDb;
import com.nakahama.simpenbackend.User.service.UserServiceImpl;
import com.nakahama.simpenbackend.util.ResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserDb userDb;

    @Autowired
    AuthService authService;

    @PostMapping("")
    public ResponseEntity<Object> AddUser(@Valid @RequestBody AddUserRequestDTO addUserRequestDTO) {
        UserModel newUser = userService.addUser(addUserRequestDTO.getEmail(), addUserRequestDTO.getRole(),
                addUserRequestDTO.getNama());
        return ResponseUtil.okResponse(newUser, "Success");
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllUser() {
        Map<String, List<UserModel>> listUser = userService.getAllUsersGroupedByCategory();

        List<Map<String, Object>> contentList = new ArrayList<>();
        for (Map.Entry<String, List<UserModel>> entry : listUser.entrySet()) {
            Map<String, Object> roleUserMap = new LinkedHashMap<>();
            roleUserMap.put("role", entry.getKey());
            roleUserMap.put("user", entry.getValue());
            contentList.add(roleUserMap);
        }
        return ResponseUtil.okResponse(contentList, "Success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable String id) {
        userService.deleteUser(UUID.fromString(id));
        return ResponseUtil.okResponse(null, "User dengan id " + id + " berhasil dihapus");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editUser(@PathVariable String id,
            @RequestBody EditUserRequestDTO editUserRequestDTO) {
        editUserRequestDTO.setId(UUID.fromString(id));
        UserModel user = userService.updateUser(editUserRequestDTO);
        return ResponseUtil.okResponse((user),
                "User berhasil diupdate");
    }

    
    @PutMapping("")
    public ResponseEntity<Object> editDataPengajar(@RequestBody EditDataPengajarRequestDTO pengajarRequestDTO,
            HttpServletRequest request) {
        authService.checkOwnership(request, pengajarRequestDTO.getId());
        Pengajar pengajar = userService.editDataPengajar(pengajarRequestDTO);
        return ResponseUtil.okResponse(pengajar,
                "User berhasil diupdate");
    }
}
