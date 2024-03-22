package com.nakahama.simpenbackend.Kelas.dto.Kelas;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class UpdateKelasPlaylist {

    @NotNull(message = "Kelas ID is mandatory")
    private int id;

    @NotEmpty(message = "Minimum 1 playlist pada kelas")
    private String linkPlaylist;
}
