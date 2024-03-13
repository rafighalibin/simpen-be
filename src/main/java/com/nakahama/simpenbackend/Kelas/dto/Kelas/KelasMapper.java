package com.nakahama.simpenbackend.Kelas.dto.Kelas;

import com.nakahama.simpenbackend.Kelas.dto.SesiKelas.SesiKelasDTO;
import com.nakahama.simpenbackend.Kelas.dto.SesiKelas.SesiKelasMapper;
import com.nakahama.simpenbackend.Kelas.model.JenisKelas;
import com.nakahama.simpenbackend.Kelas.model.Kelas;
import com.nakahama.simpenbackend.Kelas.model.Program;
import com.nakahama.simpenbackend.Kelas.model.SesiKelas;
import com.nakahama.simpenbackend.User.model.Pengajar;
import com.nakahama.simpenbackend.User.model.UserModel;

import java.util.*;

public class KelasMapper {

    public static Kelas toEntity(CreateKelas request, Program program, JenisKelas jenisKelas,
            Pengajar pengajar, int kelasId) {
        Kelas response = new Kelas();
        response.setKelasId(kelasId);
        response.setProgram(program);
        response.setJenisKelas(jenisKelas);
        response.setOperasional(request.getOperasional());

        response.setPengajar(pengajar);
        response.setLevel(request.getLevel());
        response.setTanggalMulai(request.getTanggalMulai());
        response.setTanggalSelesai(request.getTanggalSelesai());
        response.setJumlahMurid(request.getListMurid().size());

        if (request.getLinkGroup() != null) {
            response.setLinkGroup(request.getLinkGroup());
        }

        response.setListMurid(request.getListMurid());
        response.setListsesiKelas(new ArrayList<SesiKelas>());
        return response;
    }

    public static Kelas toEntity(UpdateKelas request, Kelas existingKelas, Pengajar pengajar) {
        Kelas response = existingKelas;
        response.setTanggalMulai(request.getTanggalMulai());
        response.setTanggalSelesai(request.getTanggalSelesai());
        response.setPengajar(pengajar);
        response.setLinkGroup(request.getLinkGroup());
        response.setListMurid(request.getListMurid());
        response.setJumlahMurid(request.getListMurid().size());
        response.setLevel(request.getLevel());
        return response;
    }

    public static ReadDetailKelas toDetailDto(Kelas createdKelas, List<SesiKelas> listSesiKelas, UserModel pengajar) {

        ReadDetailKelas response = new ReadDetailKelas();
        response.setProgramName(createdKelas.getProgram().getNama());
        response.setProgramId(createdKelas.getProgram().getId());
        response.setJenisKelasName(createdKelas.getJenisKelas().getNama());
        response.setJenisKelasId(createdKelas.getJenisKelas().getId());
        response.setListSesi(new ArrayList<SesiKelasDTO>());
        for (SesiKelas sesiKelas : listSesiKelas) {
            response.getListSesi().add(SesiKelasMapper.toDto(sesiKelas));
        }
        response.setNamaPengajar(pengajar.getNama());
        response.setTanggalMulai(createdKelas.getTanggalMulai());
        response.setTanggalSelesai(createdKelas.getTanggalSelesai());
        response.setPengajarId(createdKelas.getPengajar().getId());
        response.setLinkGroup(createdKelas.getLinkGroup());
        response.setListMurid(createdKelas.getListMurid());
        response.setLevel(createdKelas.getLevel());
        // TODO: IMPLEMENT AVERAGE RATING
        // TODO: IMPLEMENT PLATFORM
        response.setPlatform("TODO: IMPLEMENT PLATFORM");
        return response;

    }

    public static ReadKelas toReadDto(Kelas kelas) {
        ReadKelas response = new ReadKelas();
        response.setId(kelas.getKelasId());
        response.setPengajar(kelas.getPengajar().getNama());
        response.setJumlah_murid(kelas.getListMurid().size());
        response.setStatus(kelas.getStatus());
        return response;
    }

}
