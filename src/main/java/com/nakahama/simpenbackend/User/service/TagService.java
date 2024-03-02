package com.nakahama.simpenbackend.User.service;

import java.util.List;

import com.nakahama.simpenbackend.User.dto.Tag.AssignTagRequestDTO;
import com.nakahama.simpenbackend.User.dto.Tag.CreateTagRequest;
import com.nakahama.simpenbackend.User.model.Tag;

public interface TagService {
    Tag createTag(CreateTagRequest tag);

    List<Tag> getAllTag();

    void deleteTag(Tag tag);

    boolean isNamaExist(String nama);

    Tag getTagById(long id);

    Tag updateTag(Tag tag);

    Tag assignTag(AssignTagRequestDTO assignTagRequestDTO);

    Tag unassignTag(AssignTagRequestDTO unassignTagRequestDTO);

}
