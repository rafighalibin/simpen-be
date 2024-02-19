package com.nakahama.simpenbackend.model;

import java.util.*;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pengajar {
    private UUID user_id;
    private String alamat_ktp;
    private String domisili_kota;
    private byte[] foto_diri;
    private String email_pribadi;
    private String email_kalananti;
    private String backup_phone_num;
    // TODO: 20/2/24 model ini hanya untuk demo pre-sprint, belum final

}
