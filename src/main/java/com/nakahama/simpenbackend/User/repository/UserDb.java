package com.nakahama.simpenbackend.User.repository;

import com.nakahama.simpenbackend.User.model.*;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
@Transactional
public interface UserDb extends JpaRepository<UserModel, UUID> {

    UserModel findByEmail(String email);

    UserModel findByRole(String email);

    List<UserModel> findAll();

}
