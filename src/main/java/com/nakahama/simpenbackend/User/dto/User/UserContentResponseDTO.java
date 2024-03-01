package com.nakahama.simpenbackend.User.dto.User;

import java.time.LocalDate;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserContentResponseDTO {
    private UUID id;
    private String nama;
    private String email_kalananti;
    private String password;
    private String jenis_kelamin;
    private String no_telp;
    private String role;
    private Child child;

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Child {
        private long user_id;
        private String alamat_ktp;
        private String domisili_kota;
        private byte[] foto_diri;
        private String email_pribadi;
        private String backup_phone_num;
        private String no_rekening_bank;
        private String nama_pemilik_rekening;
        private byte[] foto_buku_tabungan;
        private String pendidikan_terakhir;
        private LocalDate tanggal_masuk_kontrak;
        private String pekerjaan_lainnya;
        private String NIK;
        private byte[] foto_KTP;
        private String NPWP;
        private byte[] foto_NPWP;
        private String nama_kontak_darurat;
        private String nomor_telp_kontak_darurat;
    }
}
