package com.nakahama.simpenbackend.Platform.model;

import java.util.UUID;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JadwalZoom {
    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "zoom_id")
    private Zoom zoom;

    @Column(name = "waktu")
    private LocalDateTime waktu;
}
