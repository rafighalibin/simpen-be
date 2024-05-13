package com.nakahama.simpenbackend.Kelas.dto.Kelas;

import java.util.*;

import com.nakahama.simpenbackend.Kelas.dto.Murid.ReadMurid;
import com.nakahama.simpenbackend.Kelas.dto.SesiKelas.SesiKelasDTO;
import com.nakahama.simpenbackend.Platform.dto.Ruangan.ReadRuangan;
import com.nakahama.simpenbackend.Platform.dto.Zoom.ReadZoom;

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

    private UUID programId;

    private String jenisKelasName;

    private UUID jenisKelasId;

    private List<SesiKelasDTO> listSesi;

    private Date tanggalMulai;

    private Date tanggalSelesai;

    private UUID pengajarId;

    private String namaPengajar;

    private String linkGroup;

    private String linkPlaylist;

    private List<ReadMurid> listMurid;

    private int level;

    private ReadZoom zoom; // need to delete after platform model created (sprint 2)

    private ReadRuangan ruangan; // need to delete after platform model created (sprint 2)

    private String modaPertemuan;

    private String status;

}
