package com.nakahama.simpenbackend.Kelas.model;

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
    private UUID jenisKelasId = UUID.randomUUID();

    @NotNull
    private String nama;

    @NotNull
    private int pertemuan;

    @NotNull
    private String tipe;

    @NotNull
    private String bahasa;

    @NotNull
    @ManyToOne
    private UserModel picAkademik; // TODO: need to change after Akademik model is created

    @ManyToMany
    private List<Program> program;

    @OneToMany
    private List<Kelas> kelas;
}
