package com.nakahama.simpenbackend.Kelas.dto;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class CreateJenisKelasRequestDTO {
    private String nama;

    private List<String> modaPertemuan;

    private List<String> tipe;

    private List<String> bahasa;

    private UUID picAkademik;
}
