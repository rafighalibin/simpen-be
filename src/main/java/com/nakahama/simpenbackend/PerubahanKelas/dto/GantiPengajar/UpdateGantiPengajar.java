package com.nakahama.simpenbackend.PerubahanKelas.dto.GantiPengajar;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class UpdateGantiPengajar {

    @NotNull(message = "Id GantiPengajar is mandatory")
    private UUID id;

    private UUID pengajarPenggantiId;

    private String alasan;

}
