package com.nakahama.simpenbackend.Feedback.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackResponseDTO {

    private UUID id;

    private String namaPengajar;

    private String namaProgram;

    private LocalDateTime tanggalPembuatan;

    private String isi;

    private double rating;

    private boolean isFinished = false;

    private boolean isDeleted = false;

    private UUID idPengajar;
}
