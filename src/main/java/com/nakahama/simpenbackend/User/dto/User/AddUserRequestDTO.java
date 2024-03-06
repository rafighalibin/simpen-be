package com.nakahama.simpenbackend.User.dto.User;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequestDTO {

    @NotBlank(message = "Email required")
    String email;

    @NotBlank(message = "Role required")
    String role;

    @NotBlank(message = "Nama required")
    String nama;
}
