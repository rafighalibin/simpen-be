package com.nakahama.simpenbackend.Kelas.model;

import com.nakahama.simpenbackend.User.model.UserModel;
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
    private UUID sesi_id = UUID.randomUUID();

    @NotNull
    private UserModel pengajar; // need to change after Pengajar model is created

    private UserModel pengajar_pengganti; // need to change after Pengajar model is created

    @ManyToOne
    @NotNull
    private Kelas kelas;

    @NotNull
    private String platform; //need to change after platform model is created (Sprint 2)

    @NotNull
    private LocalDateTime waktu_pelaksanaan;

    @NotNull
    private String status;
}
