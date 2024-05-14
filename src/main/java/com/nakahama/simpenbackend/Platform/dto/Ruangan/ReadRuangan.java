package com.nakahama.simpenbackend.Platform.dto.Ruangan;

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
public class ReadRuangan {
    private UUID id;

    private String nama;

    private String cabang;

    private int kapasitas;

}
