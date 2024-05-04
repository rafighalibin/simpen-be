package com.nakahama.simpenbackend.Feedback.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nakahama.simpenbackend.Kelas.model.Kelas;
import com.nakahama.simpenbackend.User.model.Pengajar;

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
    @JoinColumn(name = "pengajar")
    @JsonIgnore
    private Pengajar pengajar;

    @ManyToOne
    @JoinColumn(name = "kelas")
    @JsonIgnore
    private Kelas kelas;

    @Column(name = "tanggal_pembuatan")
    private LocalDateTime tanggalPembuatan;

    @Column(name = "isi")
    private String isi;

    @Column(name = "rating")
    private double rating;

    @Column(name = "status")
    private boolean isFinished = false;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

}
