package com.nakahama.simpenbackend.Kelas.dto.Murid;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class ReadMurid {

    private UUID id;

    private String nama;

    private String namaOrtu;

    private String emailOrtu;

    private String noHpOrtu;
}
