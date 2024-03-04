package com.nakahama.simpenbackend.User.model;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_model")
@SQLDelete(sql = "UPDATE user_model SET is_deleted = true WHERE id=?")
public class UserModel implements Serializable {
    @Id
    private UUID id = UUID.randomUUID();

    @Size(max = 100)
    @Column(name = "nama")
    private String nama;

    @Size(max = 100)
    @Column(name = "email_kalananti")
    private String email;

    @Size(max = 100)
    @Column(name = "email_pribadi")
    private String emailPribadi;

    @Size(max = 100)
    @Column(name = "password")
    private String password;

    @Size(max = 100)
    @Column(name = "jenis_kelamin")
    private String jenisKelamin;

    @Size(max = 100)
    @Column(name = "no_telp")
    private String noTelp;

    @Size(max = 100)
    @Column(name = "alamat_ktp")
    private String alamatKTP;

    @Size(max = 100)
    @Column(name = "domisili_kota")
    private String domisiliKota;

    @Size(max = 100)
    @Column(name = "foto_diri")
    private byte[] fotoDiri;

    @Size(max = 100)
    @Column(name = "backup_phone_num")
    private String backupPhoneNum;

    @Size(max = 100)
    @Column(name = "no_rekening_bank")
    private String noRekeningBank;

    @Size(max = 100)
    @Column(name = "nama_bank")
    private String namaBank;

    @Size(max = 100)
    @Column(name = "nama_pemilik_rekening")
    private String namaPemilikRekening;

    @Size(max = 100)
    @Column(name = "foto_buku_tabungan")
    private byte[] fotoBukuTabungan;

    @Size(max = 100)
    @Column(name = "pendidikan_terakhir")
    private String pendidikanTerakhir;

    @Size(max = 100)
    @Column(name = "tanggal_masuk_kontrak")
    private String tanggalMasukKontrak;

    @Size(max = 100)
    @Column(name = "pekerjaan_lainnya")
    private String pekerjaanLainnya;

    @Size(max = 100)
    @Column(name = "NIK")
    private String NIK;

    @Size(max = 100)
    @Column(name = "foto_KTP ")
    private byte[] fotoKTP;

    @Size(max = 100)
    @Column(name = "NPWP")
    private String NPWP;

    @Size(max = 100)
    @Column(name = "foto_NPWP")
    private byte[] fotoNPWP;

    @Size(max = 100)
    @Column(name = "nama_kontak_darurat")
    private String namaKontakDarurat;

    @Size(max = 100)
    @Column(name = "nomor_telp_kontak_darurat ")
    private String nomorTelpKontakDarurat;
    
    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @Size(max = 100)
    @Column(name = "role")
    private String role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Pengajar pengajar;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)

    private Operasional operasional;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Akademik akademik;

}
