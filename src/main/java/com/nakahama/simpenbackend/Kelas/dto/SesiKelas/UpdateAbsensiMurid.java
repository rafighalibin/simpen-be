package com.nakahama.simpenbackend.Kelas.dto.SesiKelas;

import java.time.LocalDateTime;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nakahama.simpenbackend.Kelas.dto.Murid.ReadMuridSesi;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAbsensiMurid {

    private Long id; // Assuming `muridId` is a Long. Change the type if it's different.

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
    private UUID sesiId; // Assuming sesiId is a UUID. Change the type if it's different.

    @NotNull(message = "isPresent cannot be null")
    private Boolean isPresent;

    private Integer rating; // Assuming rating can be null and is an Integer. Adjust as necessary.

    private String komentar; // Assuming komentar can be null.
}
