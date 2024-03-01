package com.nakahama.simpenbackend.User.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditUserRequestDTO extends AddUserRequestDTO {
    UUID id;
    String password;
    String jenisKelamin;
    String noTelp;
}
