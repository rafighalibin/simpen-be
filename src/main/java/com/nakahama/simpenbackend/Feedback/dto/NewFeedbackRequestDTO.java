package com.nakahama.simpenbackend.Feedback.dto;

import java.time.LocalDateTime;

import com.nakahama.simpenbackend.Kelas.model.Kelas;
import com.nakahama.simpenbackend.User.model.Pengajar;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewFeedbackRequestDTO {

    @NotBlank(message = "Pengajar required")
    Pengajar pengajar;

    @NotBlank(message = "Kelas required")
    Kelas kelas;

    @NotBlank(message = "Tanggal pembuatan required")
    LocalDateTime tanggalPembuatan;

}
