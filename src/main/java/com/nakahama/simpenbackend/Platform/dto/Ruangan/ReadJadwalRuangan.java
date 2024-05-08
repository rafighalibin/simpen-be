package com.nakahama.simpenbackend.Platform.dto.Ruangan;

import java.time.LocalDateTime;
import java.util.UUID;

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
public class ReadJadwalRuangan {
    private UUID id;

    private UUID platformId;

    private String nama;

    private String cabang;

    private int kapasitas;

    private LocalDateTime waktu;

    private String namaPengajar;
}
