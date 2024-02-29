package com.nakahama.simpenbackend.Kelas.dto;

import java.time.LocalDateTime;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private List<LocalDateTime> jadwalKelas;

    private Date tanggalMulai;

    private Date tanggalSelesai;

    private UUID pengajarId;

    private String linkGroup;

    private List<String> listMurid;

    private int level;

    private String platform; //need to delete after platform model created (sprint 2)

}
