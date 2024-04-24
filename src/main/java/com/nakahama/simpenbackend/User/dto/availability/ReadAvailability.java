package com.nakahama.simpenbackend.User.dto.availability;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
public class ReadAvailability {
    private UUID idPengajar;
    private String namaPengajar;
    private LocalDateTime lastUpdate;
    private List<LocalDateTime> availability;
}
