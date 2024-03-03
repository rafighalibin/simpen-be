package com.nakahama.simpenbackend.User.dto.Tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadTagResponseDTO {
    private Long id;

    private String nama;
}
