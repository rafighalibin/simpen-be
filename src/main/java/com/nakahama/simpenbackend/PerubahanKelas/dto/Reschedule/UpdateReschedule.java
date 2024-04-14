package com.nakahama.simpenbackend.PerubahanKelas.dto.Reschedule;

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
public class UpdateReschedule {

    @NotNull(message = "Id Reschedule is mandatory")
    private UUID id;

    private UUID ZoomId;

    private String alasan;

}
