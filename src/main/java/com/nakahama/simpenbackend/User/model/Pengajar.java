package com.nakahama.simpenbackend.User.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pengajar")
public class Pengajar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;

    @Size(max = 100)
    @Column(name = "alamat_ktp")
    private String alamatKtp;

    @Size(max = 100)
    @Column(name = "domisili_kota")
    private String domisiliKota;

    @Column(name = "foto_diri")
    private byte[] fotoDiri;

    @Size(max = 100)
    @Column(name = "email_pribadi")
    private String emailPribadi;

    @Size(max = 100)
    @Column(name = "backup_phone_num")
    private String backupPhoneNum;

    @Size(max = 100)
    @Column(name = "no_rekening_bank")
    private String noRekBank;

    @Size(max = 100)
    @Column(name = "nama_bank")
    private String namaBank;

    @Size(max = 100)
    @Column(name = "nama_pemilik_rekening")
    private String namaPemilikRek;

    @Column(name = "foto_buku_tabungan")
    private byte[] fotoBukuTabungan;

    @Size(max = 100)
    @Column(name = "pendidikan_terkahir")
    private String pendidikanTerakhir;

    @Column(name = "tgl_masuk_kontrak")
    private LocalDate tglMasukKontrak;

    @Size(max = 100)
    @Column(name = "pekerjaan_lainnya")
    private String pekerjaanLainnya;

    @Size(max = 100)
    @Column(name = "nik")
    private String nik;

    @Column(name = "foto_ktp")
    private byte[] fotoKtp;

    @Size(max = 100)
    @Column(name = "npwp")
    private String npwp;

    @Column(name = "foto_npwp")
    private byte[] fotoNpwp;

    @Size(max = 100)
    @Column(name = "nama_kontak_darurat")
    private String namaKontakDarutat;

    @Size(max = 100)
    @Column(name = "no_telp_darurat")
    private String noTelpDarurat;

    @ManyToMany(mappedBy = "listPengajar", fetch = FetchType.LAZY)
    @JsonIgnore
    List<Tag> listTag;
}
