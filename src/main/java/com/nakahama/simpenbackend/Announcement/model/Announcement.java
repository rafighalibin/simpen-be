package com.nakahama.simpenbackend.Announcement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "announcement")
@SQLDelete(sql = "UPDATE announcement SET is_deleted = true WHERE id=?")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_pembuat")
    private UUID idPembuat;

    @Column(name = "judul")
    private String judul;

    @Column(name = "isi")
    private String isi;

    @CreationTimestamp
    @Column(name = "tanggal_pembuatan")
    private Date tanggalPembuatan;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

}
