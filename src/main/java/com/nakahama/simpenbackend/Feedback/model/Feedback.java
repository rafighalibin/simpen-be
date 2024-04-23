package com.nakahama.simpenbackend.Feedback.model;

import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nakahama.simpenbackend.Kelas.model.Kelas;
import com.nakahama.simpenbackend.User.model.UserModel;

import java.util.UUID;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feedback")
@SQLDelete(sql = "UPDATE feedback SET is_deleted = true WHERE id=?")
public class Feedback {
    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "akun_penerima")
    @JsonIgnore
    private UserModel akunPenerima;

    @ManyToOne
    @JoinColumn(name = "akun_pembuat")
    @JsonIgnore
    private UserModel akunPembuat;

    // @ManyToOne
    // @JoinColumn(name = "kelas_pengajaran")
    // @JsonIgnore
    // private Kelas kelasPengajaran;

    @Column(name = "tanggal_pembuatan")
    private LocalDateTime tanggalPembuatan;

    @Column(name = "isi")
    private String isi;

    @Column(name = "rating")
    private int rating;

    @Column(name = "status")
    private String status;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;
}
