package com.nakahama.simpenbackend.Feedback.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nakahama.simpenbackend.Notification.model.Notification;

public interface FeedbackDb  extends JpaRepository<Notification, UUID>  {
    
}
