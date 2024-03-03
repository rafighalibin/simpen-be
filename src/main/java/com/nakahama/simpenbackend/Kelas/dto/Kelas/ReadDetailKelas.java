package com.nakahama.simpenbackend.Kelas.dto.Kelas;

import java.util.*;

import com.nakahama.simpenbackend.Kelas.dto.SesiKelas.SesiKelasDTO;
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
public class ReadDetailKelas {

    private String programName;

    private String jenisKelasName;

    private List<SesiKelasDTO> listSesi;

    private Date tanggalMulai;

    private Date tanggalSelesai;

    private UUID pengajarId;

    private String linkGroup;

    private List<String> listMurid;

    private int level;

    private String platform; // need to delete after platform model created (sprint 2)

}
