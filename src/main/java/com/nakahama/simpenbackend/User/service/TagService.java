package com.nakahama.simpenbackend.User.service;

import java.util.List;

import com.nakahama.simpenbackend.User.dto.Tag.AssignTagRequestDTO;
import com.nakahama.simpenbackend.User.dto.Tag.CreateTagRequest;
import com.nakahama.simpenbackend.User.dto.Tag.UpdateTagRequest;
import com.nakahama.simpenbackend.User.model.Pengajar;
import com.nakahama.simpenbackend.User.model.Tag;

public interface TagService {
    Tag createTag(CreateTagRequest tag);

    List<Tag> getAllTag();

    void deleteTag(Long id);

    boolean isNamaExist(String nama);

    Tag getTagById(long id);

    Tag updateTag(UpdateTagRequest tag);

    Pengajar assignTag(AssignTagRequestDTO assignTagRequestDTO);

    Pengajar unassignTag(AssignTagRequestDTO unassignTagRequestDTO);

    Tag setAssignJumlahPengajar(Tag tag);

    Tag setUnassignJumlahPengajar(Tag tag);

}
