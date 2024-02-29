package com.nakahama.simpenbackend.User.model;

import java.io.Serializable;
import java.util.UUID;

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

    @Size(max = 100)
    @Column(name = "role")
    private String role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Pengajar pengajar;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Operasional operasional;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Akademik akademik;
}
