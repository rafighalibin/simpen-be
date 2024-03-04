package com.nakahama.simpenbackend.Kelas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nakahama.simpenbackend.User.model.Pengajar;
import java.time.LocalDateTime;
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
@Table(name = "sesi_kelas")
public class SesiKelas {
    @Id
    private UUID Id = UUID.randomUUID();

    @NotNull
    @ManyToOne
    @JsonIgnore
    private Pengajar pengajar; // need to change after Pengajar model is created

    @ManyToOne
    @JsonIgnore
    private Pengajar pengajarPengganti; // need to change after Pengajar model is created

    @ManyToOne
    @NotNull
    @JsonIgnore
    private Kelas kelas;

    @NotNull
    private String platform; // need to change after platform model is created (Sprint 2)

    @NotNull
    private LocalDateTime waktuPelaksanaan;

    @NotNull
    private String status;
}
