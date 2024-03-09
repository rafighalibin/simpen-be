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
public class EditDataPengajarRequestDTO {
    UUID id;
    String password;
    String alamatKTP;
    String domisiliKota;
    byte[] fotoDiri;
    String emailPribadi;
    String backupPhoneNum;
    String noRekBank;
    String namaBank;
    String namaPemilikRek;
    byte[] fotoBukuTabungan;
    String pendidikanTerakhir;
    String pekerjaanLainnya;
    LocalDate tglMasukKontrak;
    String nik;
    byte[] fotoKtp;
    String Npwp;
    byte[] fotoNpwp;
    String namaKontakDarurat;
    String noTelpDarurat;
}
