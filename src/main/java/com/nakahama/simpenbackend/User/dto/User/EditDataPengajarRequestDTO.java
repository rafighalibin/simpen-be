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
public class EditDataPengajarRequestDTO {
    UUID id;
    String alamatKTP;
    String nama;
    String domisiliKota;
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
