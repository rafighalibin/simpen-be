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
public class CreateFee {
    @NotNull(message = "Jenis Kelas required")
    private UUID jenisKelas;

    @NotNull(message = "Program required")
    private UUID program;

    @NotNull(message = "Base fee required")
    private int baseFee;

    @NotNull(message = "Student multiplier required")
    private int studentMultiplier;

    @NotNull(message = "Max students required")
    private int maxStudents;
}
