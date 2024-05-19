package com.nakahama.simpenbackend.User.dto.Tag;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssignTagRequestDTO {
    @NotNull(message = "Id Pengajar required")
    private UUID id;
    
    private List<Long> listIdTag;
}
