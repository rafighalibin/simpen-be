package com.nakahama.simpenbackend.Feedback.service;

import java.util.*;

import com.nakahama.simpenbackend.Feedback.dto.FillFeedbackRequestDTO;
import com.nakahama.simpenbackend.Feedback.model.Feedback;

public interface FeedbackService {
    public List<Feedback> retrieveAllFeedback();

    public void generateFeedback(Kelas kelas);

    public List<Feedback> feedbackByUserId(UUID id);

    public Feedback getFeedbackById(UUID id);

    public Feedback fillFeedback(FillFeedbackRequestDTO fillFeedbackRequestDTO);

    public void deleteFeedback(UUID id);
}
