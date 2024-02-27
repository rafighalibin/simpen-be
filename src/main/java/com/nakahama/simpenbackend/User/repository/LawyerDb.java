package com.nakahama.simpenbackend.User.repository;

import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nakahama.simpenbackend.User.model.Lawyer;

import java.util.List;

@Repository
public interface LawyerDb extends JpaRepository<Lawyer, UUID> {
    List<Lawyer> findAll();
}
