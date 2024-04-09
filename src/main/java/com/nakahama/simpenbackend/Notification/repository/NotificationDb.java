package com.nakahama.simpenbackend.Notification.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nakahama.simpenbackend.Notification.model.Notification;

@Repository
public interface NotificationDb extends JpaRepository<Notification, UUID> {

    List<Notification> findAll();

}
