package com.nakahama.simpenbackend.Kelas.dto.JenisKelas;

import java.util.UUID;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @NotBlank(message = "Nama Jenis required")
    private String nama;

    @NotEmpty(message = "Bentuk Pertemuan required")
    private List<String> modaPertemuan;

    @NotEmpty(message = "Tipe required")
    private List<String> tipe;

    @NotEmpty(message = "Bahasa required")
    private List<String> bahasa;

    @NotNull(message = "Bahasa required")
    private UUID picAkademikId;
}
