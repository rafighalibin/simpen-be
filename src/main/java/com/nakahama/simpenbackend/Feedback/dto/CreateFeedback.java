package com.nakahama.simpenbackend.Feedback.dto;

import org.hibernate.validator.constraints.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class CreateFeedback {
    @NotBlank(message = "ID Penerima required")
    private UUID idPenerima;

    @NotBlank(message = "ID Pembuat required")
    private UUID idPembuat;

    @NotBlank(message = "Isi required")
    private String isi;

    @NotNull(message = "Rating required")
    private int rating;
}
