package com.nakahama.simpenbackend.Platform.model;

import java.io.Serializable;
import java.util.UUID;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.SQLDelete;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JadwalRuangan {
    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "ruangan_id")
    private Ruangan ruangan;

    @Column(name = "waktu")
    private LocalDateTime waktu;
}
