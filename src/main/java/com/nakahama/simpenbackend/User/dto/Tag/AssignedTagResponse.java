package com.nakahama.simpenbackend.User.dto.Tag;

import java.util.List;
import java.util.UUID;

import com.nakahama.simpenbackend.User.dto.User.tagDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignedTagResponse {
    private UUID idPengajar;

    private String namaPengajar;

    private List<tagDTO> listTag;

}
