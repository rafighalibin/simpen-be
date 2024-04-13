package com.nakahama.simpenbackend.Notification.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nakahama.simpenbackend.Notification.model.Notification;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface NotificationDb extends JpaRepository<Notification, UUID> {

    @Query("FROM Notification WHERE isDeleted = false")
    List<Notification> findAll();

}
