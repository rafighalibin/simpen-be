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
    private int kelas_id;

    @ManyToOne
    @NotNull
    private Program program;

    @ManyToOne
    @NotNull
    private JenisKelas jenis_kelas;

    @NotNull
    private UserModel operasional; // need to change after Operasional model is created
    
    @NotNull
    private UserModel pengajar; // need to change after Pengajar model is created

    @NotNull
    private int level;

    @NotNull
    private String link_playlist;

    @NotNull
    private String link_group;

    @NotNull
    private Date tanggal_mulai;

    @NotNull
    private Date tanggal_selesai;

    @NotNull
    private int jumlah_murid;

    @NotNull
    private float average_rating;

    @OneToMany
    private List<SesiKelas> sesi_kelas;
}
