package com.nakahama.simpenbackend.User.dto.Tag;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTagRequest {

    @NotBlank(message = "Nama tag required")
    private String nama;
}
