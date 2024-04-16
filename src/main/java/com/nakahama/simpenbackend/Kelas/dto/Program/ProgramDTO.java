package com.nakahama.simpenbackend.Kelas.dto.Program;

import java.util.UUID;

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
public class ProgramDTO {
    private UUID id;

    private String nama;

    private int jumlahLevel;

    private int jumlahPertemuan;
}
