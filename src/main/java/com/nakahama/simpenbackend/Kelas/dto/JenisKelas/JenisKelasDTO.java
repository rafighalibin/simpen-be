package com.nakahama.simpenbackend.Kelas.dto.JenisKelas;

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
public class JenisKelasDTO {

    private UUID id;

    private String nama;

    private String bahasa;

    private String pertemuan;

    private String tipe;

}
