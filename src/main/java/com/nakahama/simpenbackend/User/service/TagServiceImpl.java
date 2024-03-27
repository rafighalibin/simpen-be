package com.nakahama.simpenbackend.User.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.User.dto.Tag.AssignTagRequestDTO;
import com.nakahama.simpenbackend.User.dto.Tag.CreateTagRequest;
import com.nakahama.simpenbackend.User.dto.Tag.UpdateTagRequest;
import com.nakahama.simpenbackend.User.model.Pengajar;
import com.nakahama.simpenbackend.User.model.Tag;
import com.nakahama.simpenbackend.User.model.UserModel;
import com.nakahama.simpenbackend.User.repository.PengajarDb;
import com.nakahama.simpenbackend.User.repository.TagDb;
import com.nakahama.simpenbackend.exception.BadRequestException;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagDb tagDb;

    @Autowired
    PengajarDb pengajarDb;

    @Autowired
    UserService userService;

    @Override
    public List<Tag> getAllTag() {
        return tagDb.findAll();
    }

    @Override
    public void deleteTag(Long id) {
        Tag tag = getTagById(id);
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
    public Tag updateTag(UpdateTagRequest tagRequest) {
        Tag tag = getTagById(tagRequest.getId());
        if (tag != null) {
            tag.setNama(tagRequest.getNama());
        }
        return tagDb.save(tag);
    }

    @Override
    public Tag createTag(CreateTagRequest tagRequest) {

        Tag tag = new Tag();
        tag.setNama(tagRequest.getNama());
        tag.setListPengajar(new ArrayList<Pengajar>());
        tag.setJumlahPengajar(0);

        return tagDb.save(tag);
    }

    @Override
    public Pengajar assignTag(AssignTagRequestDTO assignTagRequestDTO) {
        UserModel user = userService.getUserById(assignTagRequestDTO.getId());
        if (!(user instanceof Pengajar)) {
            throw new BadRequestException("User with id " + assignTagRequestDTO.getId() + " is not a pengajar");
        }
        Pengajar pengajar = (Pengajar) user;
        for (Long id : assignTagRequestDTO.getListIdTag()) {
            Tag tag = getTagById(id);

            if (pengajar.getListTag().contains(tag)) {
                continue;
            }
            pengajar.getListTag().add(tag);
           setAssignJumlahPengajar(tag);
        }
        return pengajarDb.save(pengajar);
    }

    @Override
    public Tag setAssignJumlahPengajar(Tag tag) {
        int jumlahPengajar = tag.getJumlahPengajar();
        jumlahPengajar++;
        tag.setJumlahPengajar(jumlahPengajar);
        return tagDb.save(tag);
    }
    
    @Override
    public Pengajar unassignTag(AssignTagRequestDTO tagRequestDTO) {
    UserModel user = userService.getUserById(tagRequestDTO.getId());
    if (!(user instanceof Pengajar)) {
        throw new BadRequestException("User with id " + tagRequestDTO.getId() + " is not a pengajar");
    }
    Pengajar pengajar = (Pengajar) user;
    for (Long id : tagRequestDTO.getListIdTag()) {
        Tag tag = getTagById(id);

        if (!pengajar.getListTag().contains(tag)) {
            throw new BadRequestException(
                    "User with id " + tagRequestDTO.getId() + " is not assigned to tag with id " + tag.getId());
        }
        pengajar.getListTag().remove(tag);
        setUnassignJumlahPengajar(tag);
    }
    return pengajarDb.save(pengajar);

    }
    @Override
    public Tag setUnassignJumlahPengajar(Tag tag) {
        int jumlahPengajar = tag.getJumlahPengajar();
        jumlahPengajar--;
        tag.setJumlahPengajar(jumlahPengajar);
        return tagDb.save(tag);
    }

}
