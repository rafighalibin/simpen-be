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
    byte[] fotoDiri;
    String emailPribadi;
    String email;
    String jenisKelamin;
    String noTelp;
    String backupPhoneNum;
    String noRekBank;
    String namaBank;
    String namaPemilikRek;
    byte[] fotoBukuTabungan;
    String pendidikanTerakhir;
    String pekerjaanLainnya;
    Date tglMasukKontrak;
    String nik;
    byte[] fotoKtp;
    String Npwp;
    byte[] fotoNpwp;
    String namaKontakDarurat;
    String noTelpDarurat;
}
