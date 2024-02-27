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
    private UUID program_id = UUID.randomUUID();

    @NotNull
    private String nama;

    @NotNull
    private int jumlah_level;

    @NotNull
    private int jumlah_pertemuan;

    @ManyToMany
    private List<JenisKelas> jenis_kelas;

    @OneToMany
    private List<Kelas> kelas;
}
