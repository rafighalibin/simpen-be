package com.nakahama.simpenbackend.Kelas.dto.Kelas;

import java.time.LocalDateTime;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nakahama.simpenbackend.User.model.UserModel;

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
public class CreateKelas {
    @NotBlank(message = "Program ID is mandatory")
    private UUID programId;

    @NotBlank(message = "Jenis Kelas ID is mandatory")
    private UUID jenisKelasId;

    @NotEmpty(message = "Minimum 1 jadwal kelas")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private List<LocalDateTime> jadwalKelas;

    @NotNull(message = "Tanggal Mulai is mandatory")
    private Date tanggalMulai;

    @NotNull(message = "Tanggal Selesai is mandatory")
    private Date tanggalSelesai;

    @NotNull(message = "Pengajar ID is mandatory")
    private UUID pengajarId;

    @NotBlank(message = "Link Group is mandatory")
    private String linkGroup;

    @NotEmpty(message = "Minimum 1 murid pada kelas")
    private List<String> listMurid;

    @NotNull(message = "Level is mandatory")
    @Min(value = 0, message = "Tidak Boleh Kurang dari 0")
    private int level;

    @NotBlank(message = "Platform is mandatory")
    private String platform; // need to delete after platform model created (sprint 2)

    // assigned at controller
    private UserModel operasional;

}
