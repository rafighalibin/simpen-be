package com.nakahama.simpenbackend.User.repository;

import com.nakahama.simpenbackend.User.model.*;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface UserDb extends JpaRepository<UserModel, UUID> {

    Optional<UserModel> findById(UUID id);

    UserModel findByEmail(String email);

}
