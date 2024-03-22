package com.nakahama.simpenbackend.Kelas.model;

import java.io.Serializable;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "murid_sesi")
public class MuridSesi implements Serializable {

    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne
    private Murid murid;

    @ManyToOne
    private SesiKelas sesiKelas;

    private Boolean isPresent;

    private int rating;

    private String komentar;

}
