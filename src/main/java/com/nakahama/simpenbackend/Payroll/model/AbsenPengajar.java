package com.nakahama.simpenbackend.Payroll.model;

import java.util.UUID;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import com.nakahama.simpenbackend.Kelas.model.Kelas;
import com.nakahama.simpenbackend.Kelas.model.SesiKelas;
import com.nakahama.simpenbackend.User.model.Pengajar;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "absen_pengajar")
public class AbsenPengajar {
    @Id
    private UUID id = UUID.randomUUID();

    @NotNull
    @ManyToOne
    private Pengajar pengajar;

    @NotNull
    @ManyToOne
    private Kelas kelas;

    @NotNull
    @ManyToOne
    private SesiKelas sesiKelas;

    @NotNull
    private Integer jumlahFee = 200;

}
