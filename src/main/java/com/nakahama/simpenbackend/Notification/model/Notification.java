package com.nakahama.simpenbackend.Notification.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nakahama.simpenbackend.User.model.UserModel;

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

    @ManyToOne
    @JoinColumn(name = "akun_penerima")
    @JsonIgnore
    private UserModel akunPenerima;

    @Column(name = "judul")
    private String judul;

    @ElementCollection
    @CollectionTable(name = "notification_isi", joinColumns = @JoinColumn(name = "notification_id"))
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    private Map<String, String> isi = new HashMap<>();

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "is_opened")
    private boolean isOpened = false;

    @Column(name = "is_hidden")
    private boolean isHidden = false;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @Column(name = "tipe")
    private int tipe;

    @Column(name = "tanggal_pembuatan")
    private LocalDateTime tanggalPembuatan;

    // Tipe

    // Mitra Pengajar
    // 1: Assign kelas --> Attr: id Kelas, jam kelas, hari kelas
    // 2: Feedback --> Attr: nama pemberi, isi belom
    // 3: Setuju/tolak perubahan jadwal --> Attr: belom
    // 4: Setuju/tolak pengajar pengganti

    // Operasional
    // 5: Req perubahan jadwal
    // 6: Req pengajar pengganti

    // Akademik
    // 8: Mitra pengajar tidak aktif
    // 7: Assign PIC kelas

}
