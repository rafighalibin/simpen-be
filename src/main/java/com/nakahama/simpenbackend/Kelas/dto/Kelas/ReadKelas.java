package com.nakahama.simpenbackend.Kelas.dto.Kelas;

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
public class ReadKelas {
    private int id;

    private String pengajar;

    private int jumlah_murid;

    private String status;
}
