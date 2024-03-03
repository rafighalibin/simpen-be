package com.nakahama.simpenbackend.Kelas.dto.JenisKelas;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class CreateJenisKelas {
    private UUID id;

    @NotBlank(message = "Nama Jenis required")
    private String nama;

    @NotBlank(message = "Bentuk Pertemuan required")
    private String pertemuan;

    @NotBlank(message = "Tipe required")
    private String tipe;

    @NotBlank(message = "Bahasa required")
    private String bahasa;

    @NotNull(message = "Bahasa required")
    private UUID picAkademikId;
}
