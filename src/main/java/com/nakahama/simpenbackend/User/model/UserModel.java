package com.nakahama.simpenbackend.User.model;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_model")
@SQLDelete(sql = "UPDATE user_model SET is_deleted = true WHERE id=?")
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

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @Size(max = 100)
    @Column(name = "role")
    private String role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Pengajar pengajar;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)

    private Operasional operasional;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Akademik akademik;
}
