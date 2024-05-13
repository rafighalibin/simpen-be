package com.nakahama.simpenbackend.Platform.dto;

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
public class ReadJadwal {
    private UUID id;

    private String title;

    private LocalDateTime start;

    private LocalDateTime end;

    private String description;
}
