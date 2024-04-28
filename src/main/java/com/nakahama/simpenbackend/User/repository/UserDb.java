package com.nakahama.simpenbackend.User.repository;

import com.nakahama.simpenbackend.User.model.*;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;
import java.time.LocalDateTime;

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

    @Query("FROM UserModel WHERE email = :email")
    UserModel findByEmailIncludingDeleted(@Param("email") String email);

    @Query("SELECT U FROM UserModel U WHERE U.role = 'pengajar' AND U.isDeleted = false AND EXISTS (SELECT A FROM Availability A WHERE A.pengajar = U AND A.waktu BETWEEN :start AND :end)")
    List<UserModel> findPengajarByAvailability(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

}
