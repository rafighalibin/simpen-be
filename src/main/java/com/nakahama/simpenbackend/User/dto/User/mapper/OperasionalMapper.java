package com.nakahama.simpenbackend.User.dto.User.mapper;

import org.mapstruct.Mapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.nakahama.simpenbackend.User.dto.User.EditDataUserRequestDTO;
import com.nakahama.simpenbackend.User.model.Operasional;

@Mapper(componentModel = "spring")
public interface OperasionalMapper {
    static Operasional toEntity(EditDataUserRequestDTO request, Operasional userExisting) {

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    if (request.getNama() != null)
        userExisting.setNama(request.getNama());
    if (request.getPassword() != null)
        userExisting.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
    if (request.getEmailPribadi() != null)
        userExisting.setEmailPribadi(request.getEmailPribadi());
    if (request.getEmail() != null)
        userExisting.setEmail(request.getEmail());
    if (request.getJenisKelamin() != null)
        userExisting.setJenisKelamin(request.getJenisKelamin());
    if (request.getNoTelp() != null)
        userExisting.setNoTelp(request.getNoTelp());
        return userExisting;
    }
}
