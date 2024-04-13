package com.nakahama.simpenbackend.Platform.model;

import java.util.UUID;

import com.nakahama.simpenbackend.Kelas.model.SesiKelas;

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
@Table(name = "jadwal_ruangan")
public class JadwalRuangan {
    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "ruangan_id")
    private Ruangan ruangan;

    @Column(name = "waktu")
    private LocalDateTime waktu;

    @OneToOne
    private SesiKelas sesiKelas;
}
