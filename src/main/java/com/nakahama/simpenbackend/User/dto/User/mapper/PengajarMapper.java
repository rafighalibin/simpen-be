package com.nakahama.simpenbackend.User.dto.User.mapper;

import org.mapstruct.Mapper;

import com.nakahama.simpenbackend.User.dto.User.EditDataPengajarRequestDTO;
import com.nakahama.simpenbackend.User.model.Pengajar;

@Mapper(componentModel = "spring")
public interface PengajarMapper { 
  static Pengajar toEntity(EditDataPengajarRequestDTO request, Pengajar pengajarExisting) {
    if (request.getAlamatKTP() != null)
        pengajarExisting.setAlamatKTP(request.getAlamatKTP());
    if (request.getDomisiliKota() != null)
        pengajarExisting.setDomisiliKota(request.getDomisiliKota());
    if (request.getNama() != null)
        pengajarExisting.setNama(request.getNama());
    if (request.getFotoDiri() != null)
        pengajarExisting.setFotoDiri(request.getFotoDiri());
    if (request.getEmailPribadi() != null)
        pengajarExisting.setEmailPribadi(request.getEmailPribadi());
    if (request.getEmail() != null)
        pengajarExisting.setEmail(request.getEmail());
    if (request.getJenisKelamin() != null)
        pengajarExisting.setJenisKelamin(request.getJenisKelamin());
    if (request.getNoTelp() != null)
        pengajarExisting.setNoTelp(request.getNoTelp());
    if (request.getBackupPhoneNum() != null)
        pengajarExisting.setBackupPhoneNum(request.getBackupPhoneNum());
    if (request.getNoRekBank() != null)
        pengajarExisting.setNoRekBank(request.getNoRekBank());
    if (request.getNamaBank() != null)
        pengajarExisting.setNamaBank(request.getNamaBank());
    if (request.getNamaPemilikRek() != null)
        pengajarExisting.setNamaPemilikRek(request.getNamaPemilikRek());
    if (request.getFotoBukuTabungan() != null)
        pengajarExisting.setFotoBukuTabungan(request.getFotoBukuTabungan());
    if (request.getPendidikanTerakhir() != null)
        pengajarExisting.setPendidikanTerakhir(request.getPendidikanTerakhir());
    if (request.getTglMasukKontrak() != null)
        pengajarExisting.setTglMasukKontrak(request.getTglMasukKontrak());
    if (request.getPekerjaanLainnya() != null)
        pengajarExisting.setPekerjaanLainnya(request.getPekerjaanLainnya());
    if (request.getNik() != null)
        pengajarExisting.setNik(request.getNik());
    if (request.getFotoKtp() != null)
        pengajarExisting.setFotoKtp(request.getFotoKtp());
    if (request.getNpwp() != null)
        pengajarExisting.setNpwp(request.getNpwp());
    if (request.getFotoNpwp() != null)
        pengajarExisting.setFotoNpwp(request.getFotoNpwp());
    if (request.getNamaKontakDarurat() != null)
        pengajarExisting.setNamaKontakDarurat(request.getNamaKontakDarurat());
    if (request.getNoTelpDarurat() != null)
        pengajarExisting.setNoTelpDarurat(request.getNoTelpDarurat());     
        return pengajarExisting;
    }
    
    
}
