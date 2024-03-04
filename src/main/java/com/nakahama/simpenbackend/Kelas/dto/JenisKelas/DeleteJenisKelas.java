package com.nakahama.simpenbackend.Kelas.dto.JenisKelas;

import java.util.UUID;

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
public class DeleteJenisKelas {

    @NotNull(message = "Id Jenis required")
    private UUID id;

}
