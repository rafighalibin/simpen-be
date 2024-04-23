package com.nakahama.simpenbackend.Feedback.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public class UpdateFeedback extends CreateFeedback{
    @NotBlank(message = "ID required")
    private UUID id;
}
