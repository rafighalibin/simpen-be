package com.nakahama.simpenbackend.User.model;

import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lawyer")
public class Lawyer {
    @Id
    private UUID user_id;

    private String alamat_ktp;
    private String domisili_kota;
    private byte[] foto_diri;
    private String email_pribadi;
    private String email_kalananti;
    private String backup_phone_num;
}
