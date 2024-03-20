package com.nakahama.simpenbackend.Kelas.dto.Murid;

import java.util.*;

import com.nakahama.simpenbackend.Kelas.model.Murid;
import com.nakahama.simpenbackend.Kelas.model.MuridKelas;
import com.nakahama.simpenbackend.Kelas.model.MuridSesi;

public class MuridMapper {

    public static ReadMurid toReadDto(Murid request) {
        ReadMurid response = new ReadMurid();
        response.setId(request.getId());
        response.setNama(request.getNama());
        response.setNamaOrtu(request.getNamaOrtu());
        response.setEmailOrtu(request.getEmailOrtu());
        response.setNoHpOrtu(request.getNoHpOrtu());

        return response;
    }

    public static Murid toEntity(CreateMurid request) {
        Murid response = new Murid();
        response.setNama(request.getNama());
        response.setTanggalLahir(request.getTanggalLahir());
        response.setNamaOrtu(request.getNamaOrtu());
        response.setEmailOrtu(request.getEmailOrtu());
        response.setNoHpOrtu(request.getNoHpOrtu());
        response.setNote(request.getNote());

        return response;
    }

    public static Murid toEntity(Murid muridToUpdate, UpdateMurid request) {
        Murid response = muridToUpdate;

        response.setId(request.getId());
        if (request.getNama() != null)
            response.setNama(request.getNama());
        if (request.getTanggalLahir() != null)
            response.setTanggalLahir(request.getTanggalLahir());
        if (request.getNamaOrtu() != null)
            response.setNamaOrtu(request.getNamaOrtu());
        if (request.getEmailOrtu() != null)
            response.setEmailOrtu(request.getEmailOrtu());
        if (request.getNoHpOrtu() != null)
            response.setNoHpOrtu(request.getNoHpOrtu());
        if (request.getNote() != null)
            response.setNote(request.getNote());

        return response;
    }

    public static ReadDetailMurid toReadDetailDto(Murid request) {

        ReadDetailMurid response = new ReadDetailMurid();
        response.setId(request.getId());
        response.setNama(request.getNama());
        response.setTanggalLahir(request.getTanggalLahir());
        response.setNamaOrtu(request.getNamaOrtu());
        response.setEmailOrtu(request.getEmailOrtu());
        response.setNoHpOrtu(request.getNoHpOrtu());
        response.setNote(request.getNote());

        return response;

    }

    public static List<ReadMuridSesi> toListReadMuridSesi(List<MuridSesi> listMuridSesi) {
        List<ReadMuridSesi> response = new ArrayList<ReadMuridSesi>();
        for (MuridSesi muridSesi : listMuridSesi) {
            response.add(toReadMuridSesi(muridSesi));
        }
        return response;
    }

    private static ReadMuridSesi toReadMuridSesi(MuridSesi muridSesi) {
        ReadMuridSesi response = new ReadMuridSesi();

        response.setMuridId(muridSesi.getMurid().getId());
        response.setSesiId(muridSesi.getSesiKelas().getId());
        response.setIsPresent(muridSesi.getIsPresent());
        response.setRating(muridSesi.getRating());

        return response;
    }

    public static List<ReadMurid> toListMuridKelas(List<MuridKelas> listMurid) {
        List<ReadMurid> response = new ArrayList<ReadMurid>();
        for (MuridKelas murid : listMurid) {
            response.add(toReadDto(murid.getMurid()));
        }
        return response;
    }

}
