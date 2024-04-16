package com.nakahama.simpenbackend.Kelas.dto.JenisKelas;

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
public class FindJenisKelas {
    private String nama;

    private String tipe;

    private String bahasa;

    private String modaPertemuan;
}
