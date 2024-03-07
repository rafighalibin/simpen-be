package com.nakahama.simpenbackend.User.dto.Tag.mapper;

import java.util.*;

import com.nakahama.simpenbackend.User.dto.Tag.AssignedTagResponse;
import com.nakahama.simpenbackend.User.dto.User.UserDTO;
import com.nakahama.simpenbackend.User.model.Pengajar;
import com.nakahama.simpenbackend.User.model.Tag;

public class AssignedTagMapper {

    public static List<AssignedTagResponse> toDto(List<Tag> listTag) {
        List<AssignedTagResponse> listAssignedTag = new ArrayList<>();

        for (Tag tag : listTag) {
            AssignedTagResponse assignedTagResponse = new AssignedTagResponse();
            assignedTagResponse.setTag_id(tag.getId());
            assignedTagResponse.setNama_tag(tag.getNama());

            List<UserDTO> listUser = new ArrayList<>();

            for (Pengajar pengajar : tag.getListPengajar()) {
                UserDTO userDTO = new UserDTO();
                userDTO.setId(pengajar.getId());
                userDTO.setNama(pengajar.getNama());
                listUser.add(userDTO);
            }

            assignedTagResponse.setListUser(listUser);
            listAssignedTag.add(assignedTagResponse);
        }
        return listAssignedTag;
    }
}
