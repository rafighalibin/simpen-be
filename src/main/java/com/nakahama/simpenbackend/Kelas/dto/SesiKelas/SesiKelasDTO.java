package com.nakahama.simpenbackend.Kelas.dto.SesiKelas;

import java.time.LocalDateTime;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nakahama.simpenbackend.Platform.dto.Zoom.ReadZoom;

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
public class SesiKelasDTO {

    private UUID sesi_id;

    private String Status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime waktuPelaksanaan;

    private ReadZoom zoom;
}
