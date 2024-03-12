package com.nakahama.simpenbackend.Kelas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nakahama.simpenbackend.User.model.Pengajar;
import com.nakahama.simpenbackend.User.model.UserModel;
import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    @ManyToOne
    private UserModel operasional; // need to change after Operasional model is created

    @NotNull
    @ManyToOne
    private Pengajar pengajar; // need to change after Pengajar model is created

    @NotNull
    private int level;

    private String linkPlaylist;

    private String linkGroup;

    @NotNull
    private Date tanggalMulai;

    @NotNull
    private Date tanggalSelesai;

    @NotNull
    private int jumlahMurid;

    private float averageRating;

    @OneToMany
    private List<SesiKelas> listsesiKelas;

    @OneToMany(mappedBy = "kelas", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MuridKelas> muridKelas;

    private List<String> listMurid;

    private String status = "Scheduled";

    private Boolean isDeleted = false;
}
