package com.nakahama.simpenbackend.Kelas.model;

import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "program")
public class Program {
    @Id
    private UUID programId = UUID.randomUUID();

    @NotNull
    private String nama;

    @NotNull
    private int jumlahLevel;

    @NotNull
    private int jumlahPertemuan;

    @ManyToMany
    private List<JenisKelas> jenisKelas;

    @OneToMany
    private List<Kelas> kelas;
}
