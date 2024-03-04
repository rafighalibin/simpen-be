package com.nakahama.simpenbackend.Kelas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nakahama.simpenbackend.User.model.UserModel;
import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jenis_kelas")
public class JenisKelas {
    @Id
    private UUID Id = UUID.randomUUID();

    @NotNull
    private String nama;

    // 0 = Online, 1 = Offline, 2 = Hybrid
    @NotNull
    private String pertemuan;

    @NotNull
    private String tipe;

    @NotNull
    private String bahasa;

    @NotNull(message = "PIC Akademik required")
    @ManyToOne
    @JsonIgnore
    private UserModel picAkademik; // TODO: need to change after Akademik model is created

    @ManyToMany
    @JsonIgnore
    private List<Program> program;

    @OneToMany
    @JsonIgnore
    private List<Kelas> kelas;
}
