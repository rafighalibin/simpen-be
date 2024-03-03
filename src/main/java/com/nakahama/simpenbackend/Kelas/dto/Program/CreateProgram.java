package com.nakahama.simpenbackend.Kelas.dto.Program;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.Min;
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
public class CreateProgram {

    private UUID Id;

    @NotBlank(message = "Nama Program required")
    private String nama;

    @NotNull(message = "Jumlah Level required")
    @Min(value = 1, message = "Jumlah Level minimum 1")
    private int jumlahLevel;

    @NotNull(message = "Jumlah Pertemuan required")
    @Min(value = 1, message = "Jumlah Pertemuan minimum 1")
    private int jumlahPertemuan;

    @NotEmpty(message = "List Id Jenis Kelas required")
    private List<UUID> jenisKelas;

}
