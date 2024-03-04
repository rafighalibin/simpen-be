package com.nakahama.simpenbackend.User.dto.User;

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
    String password;
    String alamatKTP;
    String domisiliKota;
    byte[] fotoDiri;
    String emailPribadi;
    String backupPhoneNum;
    String noRekeningBank;
    String namaBank;
    String namaPemilikRekening;
    byte[] fotoBukuTabungan;
    String pendidikanTerakhir;
    String tanggalMasukKontrak;
    String pekerjaanLainnya;
    String NIK;
    byte[] fotoKTP;
    String NPWP;
    byte[] fotoNPWP;
    String namaKontakDarurat;
    String nomorTelpKontakDarurat;
}
