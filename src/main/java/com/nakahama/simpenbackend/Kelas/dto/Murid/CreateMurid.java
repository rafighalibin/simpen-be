package com.nakahama.simpenbackend.Kelas.dto.Murid;

import java.util.*;

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
public class CreateMurid {

    private int id;

    @NotBlank(message = "Nama required")
    private String nama;

    @NotNull(message = "Tanggal Mulai is mandatory")
    private Date tanggalLahir;

    @NotBlank(message = "Nama Orang Tua required")
    private String namaOrtu;

    @NotBlank(message = "Email Orang Tua required")
    private String emailOrtu;

    @NotBlank(message = "Nomor HP Orang Tua required")
    private String noHpOrtu;

    private String note;
}
