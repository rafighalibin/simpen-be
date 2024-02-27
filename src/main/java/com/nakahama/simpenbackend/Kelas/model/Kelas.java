package com.nakahama.simpenbackend.Kelas.model;

import com.nakahama.simpenbackend.User.model.UserModel;
import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "kelas")
public class Kelas {
    @Id
    private int kelasId;

    @ManyToOne
    @NotNull
    private Program program;

    @ManyToOne
    @NotNull
    private JenisKelas jenisKelas;

    @NotNull
    private UserModel operasional; // need to change after Operasional model is created
    
    @NotNull
    private UserModel pengajar; // need to change after Pengajar model is created

    @NotNull
    private int level;

    @NotNull
    private String linkPlaylist;

    @NotNull
    private String linkGroup;

    @NotNull
    private Date tanggalMulai;

    @NotNull
    private Date tanggalSelesai;

    @NotNull
    private int jumlahMurid;

    @NotNull
    private float averageRating;

    @OneToMany
    private List<SesiKelas> sesiKelas;
}
