package com.nakahama.simpenbackend.Payroll.dto;

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
public class ReadAbsenPengajar {
    private String programName;

    private String jenisKelasName;

    private String pengajar;

    private int id;

    private int fee;
}
