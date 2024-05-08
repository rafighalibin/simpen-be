package com.nakahama.simpenbackend.Platform.dto.Zoom;

import java.util.UUID;

import java.util.List;

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
public class ReadDetailZoom {

    private UUID id;

    private String nama;

    private String hostKey;

    private String link;

    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private List<ReadJadwalZoom> jadwalPemakaian;
}
