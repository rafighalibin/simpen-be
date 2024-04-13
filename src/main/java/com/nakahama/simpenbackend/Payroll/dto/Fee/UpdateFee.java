package com.nakahama.simpenbackend.Payroll.dto.Fee;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFee {
    @NotNull(message = "id required")
    private UUID id;

    @NotNull(message = "Base fee required")
    private int baseFee;

    @NotNull(message = "Student multiplier required")
    private int studentMultiplier;

    @NotNull(message = "Max students required")
    private int maxStudents;
}
