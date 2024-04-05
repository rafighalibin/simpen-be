package com.nakahama.simpenbackend.Announcement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nakahama.simpenbackend.Announcement.model.Announcement;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface AnnouncementDb extends JpaRepository<Announcement, Long> {

    @Query("FROM Announcement WHERE isDeleted = false")
    List<Announcement> findAll();

}
