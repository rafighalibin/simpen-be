package com.nakahama.simpenbackend.Platform.dto.Zoom;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
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
public class UpdateZoomRequest {

    // Assigned at controller
    private UUID id;

    @NotBlank(message = "Nama is mandatory")
    private String nama;

    @NotBlank(message = "Host Key is mandatory")
    private String hostKey;

    @NotBlank(message = "Link is mandatory")
    private String link;
}
