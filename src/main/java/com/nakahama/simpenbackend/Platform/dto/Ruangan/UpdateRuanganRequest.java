package com.nakahama.simpenbackend.Platform.dto.Ruangan;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class UpdateRuanganRequest {

    // Assigned at controller
    private UUID id;

    @NotBlank(message = "Nama is mandatory")
    private String nama;

    @NotBlank(message = "Cabang is mandatory")
    private String cabang;

    @NotNull(message = "Kapasitas is mandatory")
    @Min(value = 1, message = "Kapasitas must be greater than 0")
    private int kapasitas;
}
