package com.nakahama.simpenbackend.Platform.dto.Zoom;

import java.time.LocalDateTime;
import java.util.UUID;

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
public class ReadJadwalZoom {

    private UUID id;

    private UUID platformId;

    private String nama;

    private String hostKey;

    private String link;

    private LocalDateTime waktu;
}
