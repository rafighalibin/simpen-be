package com.nakahama.simpenbackend.Kelas.dto.Kelas;

import jakarta.validation.constraints.NotBlank;
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
public class AddPlaylist {
    @NotNull(message = "Id Kelas is mandatory")
    private int id;

    @NotBlank(message = "Platform is mandatory")
    private String linkPlaylist;

}
