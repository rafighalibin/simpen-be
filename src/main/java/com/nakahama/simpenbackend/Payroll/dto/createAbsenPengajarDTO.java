package com.nakahama.simpenbackend.Payroll.dto;

import java.util.UUID;

import com.nakahama.simpenbackend.User.model.Pengajar;

import jakarta.validation.constraints.NotBlank;
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
public class createAbsenPengajarDTO {
    //assigned at controller

    Pengajar pengajar;

    @NotNull(message = "ID required")
    UUID idSesiKelas;

}