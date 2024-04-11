package com.nakahama.simpenbackend.Kelas.service;

import java.util.List;

import com.nakahama.simpenbackend.Kelas.dto.Kelas.CreateKelas;
import com.nakahama.simpenbackend.Kelas.dto.Kelas.UpdateKelas;
import com.nakahama.simpenbackend.Kelas.model.Kelas;
import com.nakahama.simpenbackend.User.model.UserModel;

public interface KelasService {

    public List<Kelas> getAll();

    public Kelas save(CreateKelas kelas);

    public Kelas getById(int id);

    public void delete(int id);

    public Kelas update(UpdateKelas kelasRequest);

    public List<Kelas> getAllKelasPengajar(UserModel userModel);

    public Kelas addPlaylist(int kelasId, String linkPlaylist);

    public void updateRating(int kelasId);
}
