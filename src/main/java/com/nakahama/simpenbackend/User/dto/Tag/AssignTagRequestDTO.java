package com.nakahama.simpenbackend.User.dto.Tag;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssignTagRequestDTO {
    @NotNull(message = "Id Tag required")
    private Long id;

    @NotEmpty(message = "List id pengajar required")
    private List<UUID> listIdPengajar;
}
