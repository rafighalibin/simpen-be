package com.nakahama.simpenbackend.Kelas.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.nakahama.simpenbackend.Kelas.dto.SesiKelas.UpdateAbsensiMurid;
import com.nakahama.simpenbackend.Kelas.model.Kelas;
import com.nakahama.simpenbackend.Kelas.model.MuridKelas;
import com.nakahama.simpenbackend.Kelas.model.SesiKelas;
import com.nakahama.simpenbackend.User.model.Pengajar;

public interface SesiKelasService {

    public List<SesiKelas> getAll();

    public SesiKelas save(SesiKelas sesiKelas);

    public SesiKelas updateJadwal(SesiKelas sesiKelas);

    public SesiKelas getById(UUID id);

    public void delete(UUID id);

    public List<SesiKelas> getByKelasId(int idKelas);

    public List<SesiKelas> getByPengajarId(UUID idPengajar);

    public SesiKelas getByPengajarPenggantiId(UUID idPengajarPengganti);

    public SesiKelas getByWaktuPermintaanBetween(Date startDate, Date endDate);

    public SesiKelas getByStatus(String status);

    public SesiKelas getByPlatformId(String idPlatform);

    public List<SesiKelas> createListSesiKelas(List<LocalDateTime> jadwalKelas, Kelas createdKelas, Pengajar pengajar,
            List<MuridKelas> listMurid, String idPlatform);

    public void uppdateAbsenSesi(UUID idSesi, List<UpdateAbsensiMurid> updateAbsensiMurid);

    public void updateStatus(SesiKelas sesiKelas);

    public List<SesiKelas> updateListSesiKelas(Kelas updatedKelas, Pengajar newpengajar, List<MuridKelas> newlistMurid);
}
