package com.nakahama.simpenbackend.User.dto.Tag.mapper;

import java.util.*;

import com.nakahama.simpenbackend.User.dto.Tag.AssignedTagResponse;
import com.nakahama.simpenbackend.User.dto.User.tagDTO;
import com.nakahama.simpenbackend.User.model.Pengajar;
import com.nakahama.simpenbackend.User.model.Tag;
import com.nakahama.simpenbackend.User.model.UserModel;

public class AssignedTagMapper {

    public static List<AssignedTagResponse> toDto(List<UserModel> listUser) {
        List<AssignedTagResponse> listAssignedTag = new ArrayList<>();

        //cari dalam listUser yang merupakan pengajar
        List<Pengajar> listPengajar = new ArrayList<>();
        for (UserModel user : listUser) {
            if (user instanceof Pengajar) {
                listPengajar.add((Pengajar) user);
            }
        }
        for (Pengajar pengajar : listPengajar) {
            AssignedTagResponse assignedTagResponse = new AssignedTagResponse();
            assignedTagResponse.setIdPengajar(pengajar.getId());
            assignedTagResponse.setNamaPengajar(pengajar.getNama());

            List<tagDTO> listTag = new ArrayList<>();

            for (Tag tag: pengajar.getListTag()) {
                tagDTO tagDTO = new tagDTO();
                tagDTO.setId(tag.getId());
                tagDTO.setNama(tag.getNama());
                tagDTO.setJumlahPengajar(tag.getJumlahPengajar());
                listTag.add(tagDTO);
            }

            assignedTagResponse.setListTag(listTag);
            listAssignedTag.add(assignedTagResponse);
        }
        return listAssignedTag;
    }
}
