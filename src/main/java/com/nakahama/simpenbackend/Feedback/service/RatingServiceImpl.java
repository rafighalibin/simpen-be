package com.nakahama.simpenbackend.Feedback.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Feedback.dto.ListRatingResponseDTO;
import com.nakahama.simpenbackend.Feedback.dto.RatingMuridResponseDTO;
import com.nakahama.simpenbackend.Kelas.model.Kelas;
import com.nakahama.simpenbackend.User.model.Pengajar;

@Service
public class RatingServiceImpl implements RatingService {

    @Override
    public ListRatingResponseDTO getRatingForPengajar(Pengajar pengajar) {
        List<Kelas> listKelas = pengajar.getKelas();
        ListRatingResponseDTO listRatingResponseDTO = new ListRatingResponseDTO();
        if (listKelas != null) {
            listRatingResponseDTO.setIdPengajar(pengajar.getId());
            listRatingResponseDTO.setNamaPengajar(pengajar.getNama());
            for (Kelas kelas : listKelas) {
                if (kelas.getAverageRating() != null) {
                    RatingMuridResponseDTO ratingMuridResponseDTO = new RatingMuridResponseDTO();
                    ratingMuridResponseDTO.setProgram(kelas.getProgram());
                    ratingMuridResponseDTO.setJenisKelas(kelas.getJenisKelas());
                    ratingMuridResponseDTO.setRating(kelas.getAverageRating());
                    ratingMuridResponseDTO.setLinkPlaylist(kelas.getLinkPlaylist());
                    // Convert Date to LocalDateTime
                    if (kelas.getTanggalSelesai() != null) {
                        Instant instant = kelas.getTanggalSelesai().toInstant();
                        LocalDateTime tanggalSelesaiLocalDateTime = LocalDateTime.ofInstant(instant,
                                ZoneId.systemDefault());
                        ratingMuridResponseDTO.setTanggalSelesai(tanggalSelesaiLocalDateTime);
                    } else {
                        ratingMuridResponseDTO.setTanggalSelesai(null); // Handle null case if needed
                    }
                    listRatingResponseDTO.getListRatingMurid().add(ratingMuridResponseDTO);
                }
            }
        }
        return listRatingResponseDTO;
    }

}
