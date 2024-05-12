package com.nakahama.simpenbackend.Platform.dto.Ruangan;

import java.util.UUID;

import java.util.List;

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
public class ReadDetailRuangan {
    private UUID id;

    private String nama;

    private String cabang;

    private int kapasitas;

    private List<ReadJadwalRuangan> jadwalPemakaian;
}
