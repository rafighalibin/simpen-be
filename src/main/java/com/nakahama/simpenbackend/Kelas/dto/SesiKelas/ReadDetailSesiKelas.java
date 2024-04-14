package com.nakahama.simpenbackend.Kelas.dto.SesiKelas;

import java.time.LocalDateTime;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nakahama.simpenbackend.Kelas.dto.Murid.ReadMuridSesi;
import com.nakahama.simpenbackend.Platform.dto.Zoom.ReadJadwalZoom;

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
public class ReadDetailSesiKelas {

    private int nomorPertemuan;

    private UUID sesi_id;

    private String Status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime waktuPelaksanaan;

    private List<ReadMuridSesi> listMuridSesi;

    private double persentaseKehadiran;

    private double averageRating;

    private ReadJadwalZoom jadwalZoom;

}
