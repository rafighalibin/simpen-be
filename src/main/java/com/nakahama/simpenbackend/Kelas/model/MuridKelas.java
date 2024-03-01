package com.nakahama.simpenbackend.Kelas.model;

import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "murid_enroll_kelas")
public class MuridKelas {
    @Id
    private UUID muridKelasId = UUID.randomUUID();

    @NotNull
    @ManyToOne
    private Murid murid;

    @NotNull
    @ManyToOne
    private Kelas kelas;

    private String linkReport;

}
