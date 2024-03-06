package com.nakahama.simpenbackend.User.repository;

import com.nakahama.simpenbackend.User.model.*;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
@Transactional
public interface UserDb extends JpaRepository<UserModel, UUID> {

    @Query("FROM UserModel WHERE email = :email AND isDeleted = false")
    UserModel findByEmail(@Param("email") String email);

    @Query("FROM UserModel WHERE role = :role AND isDeleted = false")
    UserModel findByRole(@Param("role") String role);

    @Query("FROM UserModel WHERE isDeleted = false")
    List<UserModel> findAll();

    @Query("FROM UserModel WHERE isDeleted = true")
    List<UserModel> findAllDeleted();

    @Query("FROM UserModel WHERE email = :email AND isDeleted = false")
    UserModel findByEmailIncludingDeleted(@Param("email") String email);

}
