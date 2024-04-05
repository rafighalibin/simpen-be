package com.nakahama.simpenbackend.Announcement.dto;

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
public class CreateAnnouncementDTO {
    @NotBlank(message = "Judul required")
    String judul;

    @NotBlank(message = "Isi required")
    String isi;

}
