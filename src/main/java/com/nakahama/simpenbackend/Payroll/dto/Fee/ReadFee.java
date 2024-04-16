package com.nakahama.simpenbackend.Payroll.dto.Fee;

import java.util.UUID;

import com.nakahama.simpenbackend.Kelas.dto.JenisKelas.ReadJenisKelas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReadFee {
    private UUID id;

    private UUID program;

    private ReadJenisKelas jenisKelas;

    private int baseFee;

    private int studentMultiplier;

    private int maxStudents;

    private String lastUpdated;
}
