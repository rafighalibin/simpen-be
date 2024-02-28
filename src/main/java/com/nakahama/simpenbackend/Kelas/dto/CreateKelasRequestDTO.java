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
public class CreateKelasRequestDTO {
    private UUID programId;

    private UUID jenisKelasId;

    private List<Date> jadwalKelas;

    private Date tanggalMulai;

    private Date tanggalSelesai;

    private UUID pengajarId;

    private String linkGroup;

    private List<String> listMurid;

}
