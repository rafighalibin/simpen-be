package com.nakahama.simpenbackend.User.dto.Tag;

import java.util.List;

import com.nakahama.simpenbackend.User.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignedTagResponse {
    private Long tag_id;

    private String nama_tag;

    private List<UserDTO> listUser;

}
