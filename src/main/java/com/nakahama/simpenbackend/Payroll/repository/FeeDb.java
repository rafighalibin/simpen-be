package com.nakahama.simpenbackend.Payroll.repository;

import com.nakahama.simpenbackend.Payroll.model.FeeModel;

import jakarta.transaction.Transactional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface FeeDb extends JpaRepository<FeeModel, UUID>{
}
