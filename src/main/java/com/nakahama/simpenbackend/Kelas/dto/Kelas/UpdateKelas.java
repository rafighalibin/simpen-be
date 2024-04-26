package com.nakahama.simpenbackend.Kelas.dto.Kelas;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class UpdateKelas {
    private int id;

    @NotNull(message = "Tanggal Mulai is mandatory")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date tanggalMulai;

    @NotNull(message = "Tanggal Selesai is mandatory")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date tanggalSelesai;

    @NotNull(message = "Pengajar ID is mandatory")
    private UUID pengajarId;

    @NotBlank(message = "Link Group is mandatory")
    private String linkGroup;

    @NotEmpty(message = "Minimum 1 murid pada kelas")
    private List<Integer> listMurid;

    @NotNull(message = "Level is mandatory")
    @Min(value = 0, message = "Tidak Boleh Kurang dari 0")
    private int level;

    private String linkPlaylist;

    private String status;

}
