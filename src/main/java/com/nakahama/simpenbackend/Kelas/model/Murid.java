package com.nakahama.simpenbackend.Kelas.model;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "murid")
public class Murid {
    @Id
    private UUID muridId = UUID.randomUUID();

    @NotNull
    private String nama;

    private Date tanggalLahir;

    @NotNull
    private String namaOrtu;

    @NotNull
    private String emailOrtu;

    @NotNull
    private String noHpOrtu;

    private String note;

    @OneToMany(mappedBy = "murid", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MuridKelas> muridKelas;
}
