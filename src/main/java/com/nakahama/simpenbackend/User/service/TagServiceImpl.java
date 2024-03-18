package com.nakahama.simpenbackend.User.service;

import java.util.ArrayList;
import java.util.List;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.User.dto.Tag.AssignTagRequestDTO;
import com.nakahama.simpenbackend.User.dto.Tag.CreateTagRequest;
import com.nakahama.simpenbackend.User.model.Pengajar;
import com.nakahama.simpenbackend.User.model.Tag;
import com.nakahama.simpenbackend.User.model.UserModel;
import com.nakahama.simpenbackend.User.repository.TagDb;
import com.nakahama.simpenbackend.exception.BadRequestException;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagDb tagDb;

    @Autowired
    UserService userService;

    @Override
    public List<Tag> getAllTag() {
        return tagDb.findAll();
    }

    @Override
    public void deleteTag(Tag tag) {
        tagDb.delete(tag);
    }

    @Override
    public boolean isNamaExist(String nama) {
        return getAllTag().stream().anyMatch(b -> b.getNama().equals(nama));
    }

    @Override
    public Tag getTagById(long id) {
        return tagDb.findById(id);
    }

    @Override
    public Tag updateTag(Tag tagRequest) {
        Tag tag = getTagById(tagRequest.getId());
        if (tag != null) {
            // tag.setNama(tagRequest.getNama());
            tag.setListPengajar(tagRequest.getListPengajar());
            tag.setJumlahPengajar(tagRequest.getListPengajar().size());
        }
        return tagDb.save(tag);
    }

    @Override
    public Tag createTag(CreateTagRequest tagRequest) {
        // if (isNamaExist(tagRequest.getNama())) {
        //     throw new BadRequestException("Tag with name " + tagRequest.getNama() + " already exists");
        // }

        Tag tag = new Tag();
        tag.setNama(tagRequest.getNama());
        tag.setListPengajar(new ArrayList<Pengajar>());
        tag.setJumlahPengajar(0);

        return tagDb.save(tag);
    }

    @Override
    public Tag assignTag(AssignTagRequestDTO assignTagRequestDTO) {
        // TODO: ini UserModel diganti Pengajar
        Tag tag = getTagById(assignTagRequestDTO.getId());
        for (UUID id : assignTagRequestDTO.getListIdPengajar()) {
            UserModel user = userService.getUserById(id);
            if (!(user instanceof Pengajar)) {
                throw new BadRequestException("User with id " + id + " is not a pengajar");
            }

            if (tag.getListPengajar().contains(user)) {
                throw new BadRequestException(
                        "User with id " + id + " is already assigned to tag with id " + tag.getId());
            }
            tag.getListPengajar().add((Pengajar) user);
            tag.setJumlahPengajar(tag.getListPengajar().size());
        }
        return tagDb.save(tag);
    }

    @Override
    public Tag unassignTag(AssignTagRequestDTO tagRequestDTO) {
        // TODO: ini UserModel diganti Pengajar

        Tag tag = getTagById(tagRequestDTO.getId());
        for (UUID id : tagRequestDTO.getListIdPengajar()) {
            UserModel user = userService.getUserById(id);
            if (!(user instanceof Pengajar)) {
                throw new BadRequestException("User with id " + id + " is not a pengajar");
            }

            if (!tag.getListPengajar().contains(user)) {
                throw new BadRequestException(
                        "User with id " + id + " is not assigned to tag with id " + tag.getId());
            }
            tag.getListPengajar().remove(user);
        }
        tag.setJumlahPengajar(tag.getListPengajar().size());
        return tagDb.save(tag);
    }

}
