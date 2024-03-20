package com.nakahama.simpenbackend.Kelas.dto.Murid;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ReadMuridSesi extends ReadMurid {
    private int muridId;

    private UUID sesiId;

    private Boolean isPresent;

    private int rating;

    private String komentar;
}
