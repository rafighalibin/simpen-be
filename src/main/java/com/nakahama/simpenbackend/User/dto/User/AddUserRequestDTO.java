package com.nakahama.simpenbackend.User.dto.User;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequestDTO {
    UUID id;
    String email;
    String role;
    String nama;
}
