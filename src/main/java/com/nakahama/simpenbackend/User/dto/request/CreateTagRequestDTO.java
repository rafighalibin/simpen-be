package com.nakahama.simpenbackend.User.dto.request;

import java.util.List;
import java.util.UUID;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateTagRequestDTO {
    private Long id;

    private List<UUID> listIdPengajar;

}
