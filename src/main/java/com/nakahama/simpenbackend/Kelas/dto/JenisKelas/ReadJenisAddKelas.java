package com.nakahama.simpenbackend.Kelas.dto.JenisKelas;

import java.util.List;
import java.util.UUID;

import com.nakahama.simpenbackend.Kelas.dto.Program.ProgramDTO;

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
public class ReadJenisAddKelas {
    private UUID id;

    private String nama;

    private String pertemuan;

    private String tipe;

    private String bahasa;

    private UUID picAkademikId;

    private String picAkademikNama;

    List<ProgramDTO> listProgram;

    private boolean hasFee;
}
