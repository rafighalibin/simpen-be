package com.nakahama.simpenbackend.Feedback.service;

import com.nakahama.simpenbackend.Feedback.dto.ListRatingResponseDTO;
import com.nakahama.simpenbackend.User.model.Pengajar;

public interface RatingService {
    public ListRatingResponseDTO getRatingForPengajar(Pengajar pengajar);
}
