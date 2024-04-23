package com.nakahama.simpenbackend.Feedback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Feedback.dto.CreateFeedback;
import com.nakahama.simpenbackend.Feedback.model.Feedback;
import com.nakahama.simpenbackend.Feedback.repository.FeedbackDb;
import com.nakahama.simpenbackend.User.model.Akademik;
import com.nakahama.simpenbackend.User.model.Pengajar;
import com.nakahama.simpenbackend.User.model.UserModel;
import com.nakahama.simpenbackend.User.repository.UserDb;
import com.nakahama.simpenbackend.User.service.UserService;

@Service
public class FeedbackServiceImpl implements FeedbackService{

    @Autowired
    FeedbackDb feedbackDb;

    @Autowired
    UserDb userDb;

    @Autowired
    UserService userService;

    @Override
    public void createFeedback(CreateFeedback createFeedbackRequest) {
        
        UserModel pengajar = userService.getUserById(createFeedbackRequest.getIdPenerima());

        Pengajar pengajarPenerima = (Pengajar) pengajar;

        UserModel akademik = userService.getUserById(createFeedbackRequest.getIdPembuat());

        Akademik akademikPengirim = (Akademik) akademik;

        Feedback feedback = new Feedback();
        feedback.setAkunPenerima(pengajarPenerima);
        feedback.setAkunPembuat(akademikPengirim);
        feedback.setIsi(createFeedbackRequest.getIsi());
        feedback.setStatus("Pending");
        feedbackDb.save(feedback);
    }
}
