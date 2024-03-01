package com.nakahama.simpenbackend.User.dto;

import com.nakahama.simpenbackend.User.dto.response.UserContentResponseDTO;
import com.nakahama.simpenbackend.User.model.Pengajar;
import com.nakahama.simpenbackend.User.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nama", target = "nama")
    @Mapping(source = "email", target = "email_kalananti") // Adjusted the source property name
    @Mapping(source = "jenisKelamin", target = "jenis_kelamin")
    @Mapping(source = "noTelp", target = "no_telp")
    @Mapping(source = "role", target = "role")
    @Mapping(target = "child", expression = "java(getChild(userModel))")
    UserContentResponseDTO userModelToUserContentResponseDTO(UserModel userModel);

    default UserContentResponseDTO.Child getChild(UserModel userModel) {
        if ("pengajar".equals(userModel.getRole())) {
            UserContentResponseDTO.Child child = new UserContentResponseDTO.Child();
            Pengajar pengajar = userModel.getPengajar();
            if (pengajar != null) {
                child.setUser_id(pengajar.getId());
                child.setAlamat_ktp(pengajar.getAlamatKtp());
                child.setDomisili_kota(pengajar.getDomisiliKota());
                child.setFoto_diri(pengajar.getFotoDiri());
                child.setEmail_pribadi(pengajar.getEmailPribadi());
                child.setBackup_phone_num(pengajar.getBackupPhoneNum());
                child.setNo_rekening_bank(pengajar.getNoRekBank());
                child.setNama_pemilik_rekening(pengajar.getNamaPemilikRek());
                child.setFoto_buku_tabungan(pengajar.getFotoBukuTabungan());
                child.setPendidikan_terakhir(pengajar.getPendidikanTerakhir());
                child.setTanggal_masuk_kontrak(pengajar.getTglMasukKontrak());
                child.setPekerjaan_lainnya(pengajar.getPekerjaanLainnya());
                child.setNIK(pengajar.getNik());
                child.setFoto_KTP(pengajar.getFotoKtp());
                child.setNPWP(pengajar.getNpwp());
                child.setFoto_NPWP(pengajar.getFotoNpwp());
                child.setNama_kontak_darurat(pengajar.getNamaKontakDarutat());
                child.setNomor_telp_kontak_darurat(pengajar.getNoTelpDarurat());
            }
            return child;
        }
        return null;
    }
}
