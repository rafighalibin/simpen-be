package com.nakahama.simpenbackend.Kelas.dto.Program;

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
public class UpdateProgram {

    @NotNull(message = "id required")
    private UUID Id;

    @NotBlank(message = "Nama Program required")
    private String nama;

    @NotNull(message = "Jumlah Level required")
    private int jumlahLevel;

    @NotNull(message = "Jumlah pertemuan required")
    private int jumlahPertemuan;
}
