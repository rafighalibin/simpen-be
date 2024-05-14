package com.nakahama.simpenbackend.Feedback.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nakahama.simpenbackend.Kelas.model.Kelas;
import com.nakahama.simpenbackend.User.model.Pengajar;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
