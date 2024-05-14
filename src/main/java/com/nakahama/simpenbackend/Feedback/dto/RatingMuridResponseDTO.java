package com.nakahama.simpenbackend.Feedback.dto;

import com.nakahama.simpenbackend.Kelas.model.JenisKelas;
import com.nakahama.simpenbackend.Kelas.model.Program;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingMuridResponseDTO {

    @NotBlank(message = "Program required")
    Program program;

    @NotBlank(message = "Jenis kelas required")
    JenisKelas jenisKelas;

    @NotBlank(message = "Rating required")
    double rating;

    @NotBlank(message = "Link playlist required")
    String linkPlaylist;

    LocalDateTime tanggalSelesai;
}
