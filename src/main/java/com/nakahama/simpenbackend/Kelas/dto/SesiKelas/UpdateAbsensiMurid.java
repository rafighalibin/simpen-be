package com.nakahama.simpenbackend.Kelas.dto.SesiKelas;

import java.util.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAbsensiMurid {

    private int id;

    @NotBlank(message = "Nama cannot be blank")
    private String nama;

    @NotBlank(message = "NamaOrtu cannot be blank")
    private String namaOrtu;

    @NotBlank(message = "EmailOrtu cannot be blank")
    @Email(message = "EmailOrtu should be a valid email")
    private String emailOrtu;

    @NotBlank(message = "NoHpOrtu cannot be blank")
    @Size(min = 10, max = 15, message = "NoHpOrtu must be between 10 and 15 characters")
    private String noHpOrtu;

    @NotNull(message = "SesiId cannot be null")
    private UUID sesiId;

    private Boolean isPresent;

    private Integer rating;

    private String komentar;
}
