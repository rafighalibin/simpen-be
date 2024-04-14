package com.nakahama.simpenbackend.Platform.dto.Zoom;

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
public class ReadZoom {

    private UUID id;

    private String nama;

    private String hostKey;

    private String link;
}
