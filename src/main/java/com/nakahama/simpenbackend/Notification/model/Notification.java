package com.nakahama.simpenbackend.Notification.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.springframework.cglib.core.Local;

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
@Table(name = "notification")
@SQLDelete(sql = "UPDATE notification SET is_deleted = true WHERE id=?")
public class Notification {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "akun_penerima")
    private UUID akunPenerima;

    @Column(name = "judul")
    private String judul;

    @Column(name = "isi")
    private Map<String, String> isi;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "is_opened")
    private Boolean isOpened;

    @Column(name = "is_hidden")
    private Boolean isHidden;

    @Column(name = "tipe")
    private int tipe;

    @CreationTimestamp
    @Column(name = "tanggal_pembuatan")
    private LocalDateTime tanggalPembuatan;

    // Tipe

    // Mitra Pengajar
    // 1: Assign kelas --> Attr: id Kelas, jam kelas, hari kelas
    // 2: Feedback --> Attr: nama pemberi, isi
    // 3: Setuju/tolak perubahan jadwal --> Attr:
    // 4: Setuju/tolak pengajar pengganti

    // Operasional
    // 5: Req perubahan jadwal
    // 6: Req pengajar pengganti

    // Akademik
    // 7: Mitra pengajar tidak aktif
    // 8: Assign PIC kelas

}
