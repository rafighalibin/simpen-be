package com.nakahama.simpenbackend.User.dto.availability;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class UpdateAvailabilityRequest {
    @NotNull
    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime waktu;
}
