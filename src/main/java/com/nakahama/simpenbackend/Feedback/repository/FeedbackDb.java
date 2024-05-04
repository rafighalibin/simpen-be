package com.nakahama.simpenbackend.Feedback.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nakahama.simpenbackend.Feedback.model.Feedback;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface FeedbackDb extends JpaRepository<Feedback, UUID> {

    @Query("FROM Feedback WHERE isDeleted = false")
    List<Feedback> findAll();
}
