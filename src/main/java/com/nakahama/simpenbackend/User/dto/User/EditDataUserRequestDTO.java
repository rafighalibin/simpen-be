package com.nakahama.simpenbackend.User.dto.User;


import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EditDataUserRequestDTO {
    UUID id;
    String role;
    String alamatKTP;
    String nama;
    String domisiliKota;
    String password;
    String emailPribadi;
    String fotoDiri;
    String email;
    String jenisKelamin;
    String noTelp;
    String backupPhoneNum;
    String noRekBank;
    String namaBank;
    String namaPemilikRek;
   String fotoBukuTabungan;
    String pendidikanTerakhir;
    String pekerjaanLainnya;
    Date tglMasukKontrak;
    String nik;
   String fotoKtp;
    String Npwp;
   String fotoNpwp;
    String namaKontakDarurat;
    String noTelpDarurat;
}
