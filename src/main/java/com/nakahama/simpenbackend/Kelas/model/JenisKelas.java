package com.nakahama.simpenbackend.Kelas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nakahama.simpenbackend.User.model.Akademik;
import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jenis_kelas", uniqueConstraints = @UniqueConstraint(columnNames = { "Id", "nama", "modaPertemuan",
        "tipe",
        "bahasa" }))
public class JenisKelas {
    @Id
    private UUID Id = UUID.randomUUID();

    @NotNull
    private String nama;

    @NotNull
    private String modaPertemuan;

    @NotNull
    private String tipe;

    @NotNull
    private String bahasa;

    @NotNull(message = "PIC Akademik required")
    @ManyToOne
    @JsonIgnore
    private Akademik picAkademik;

    @ManyToMany
    @JsonIgnore
    private List<Program> program;

    @OneToMany
    @JsonIgnore
    private List<Kelas> kelas;
}
