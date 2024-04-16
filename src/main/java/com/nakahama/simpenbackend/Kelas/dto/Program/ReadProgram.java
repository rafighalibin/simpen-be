package com.nakahama.simpenbackend.Kelas.dto.Program;

import java.util.List;
import java.util.UUID;

import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.JenisKelasDTO;

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
public class ReadProgram {

    private UUID Id;

    private String nama;

    private int jumlahLevel;

    private int jumlahPertemuan;

    private List<JenisKelasDTO> listJenisKelas;
}
