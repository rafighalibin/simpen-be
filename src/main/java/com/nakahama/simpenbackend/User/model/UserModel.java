package com.nakahama.simpenbackend.User.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_model")
public class UserModel implements Serializable {
    @Id
    private UUID id = UUID.randomUUID();

    @Size(max = 100)
    @Column(name = "nama")
    private String nama;

    @Size(max = 100)
    @Column(name = "email_kalananti")
    private String email;

    @Size(max = 100)
    @Column(name = "password")
    private String password;

    @Size(max = 100)
    @Column(name = "jenis_kelamin")
    private String jenisKelamin;

    @Size(max = 100)
    @Column(name = "no_telp")
    private String noTelp;

    @Column(name = "isDeleted")
    private boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Role role;
}
