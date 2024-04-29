package com.nakahama.simpenbackend.Feedback.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nakahama.simpenbackend.Feedback.model.Feedback;
import com.nakahama.simpenbackend.Feedback.service.FeedbackService;
import com.nakahama.simpenbackend.util.ResponseUtil;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @GetMapping("")
    public ResponseEntity<Object> getAllFeedback() {
        List<Feedback> listFeedback = feedbackService.retrieveAllFeedback();
        return ResponseUtil.okResponse(listFeedback, "Success");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFeedbackByUser(@PathVariable("id") String id) {
        List<Feedback> listFeedback = feedbackService.feedbackByUserId(UUID.fromString(id));
        return ResponseUtil.okResponse(listFeedback, "Success");
    }
}
