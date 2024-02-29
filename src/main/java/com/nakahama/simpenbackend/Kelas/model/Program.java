package com.nakahama.simpenbackend.Kelas.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private UUID Id = UUID.randomUUID();

    @NotNull
    private String nama;

    @NotNull
    private int jumlahLevel;

    @NotNull
    private int jumlahPertemuan;

    @ManyToMany
    @JsonIgnore
    private List<JenisKelas> jenisKelas;

    @OneToMany
    @JsonIgnore
    private List<Kelas> kelas;
}
