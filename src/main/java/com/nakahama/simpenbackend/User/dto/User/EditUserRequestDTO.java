package com.nakahama.simpenbackend.User.dto.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditUserRequestDTO {
    // assigned at controller
    UUID id;

    @NotBlank(message = "Nama required")
    String nama;

    @NotBlank(message = "Email required")
    String email;

    @NotBlank(message = "Password required")
    String password;

    @NotBlank(message = "Jenis Kelamin required")
    String jenisKelamin;

    @NotBlank(message = "Nomor Telepon required")
    String noTelp;
}
