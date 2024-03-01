package com.nakahama.simpenbackend.User.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.User.model.Pengajar;
import com.nakahama.simpenbackend.User.model.Tag;
import com.nakahama.simpenbackend.User.repository.TagDb;

@Service
public class TagServiceImpl implements TagService{
    @Autowired
    TagDb tagDb;

    @Override
    public Tag createTag(Tag tagRequest) {
        Tag tag = new Tag();
        tag.setId(tagRequest.getId());
        tag.setNama(tagRequest.getNama());
        tag.setListPengajar(new ArrayList<Pengajar>());
        tag.setJumlahPengajar(0);
        
        return tagDb.save(tag);
    }

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
        if(tag != null) {
            //tag.setNama(tagRequest.getNama());
            tag.setListPengajar(tagRequest.getListPengajar());
            tag.setJumlahPengajar(tagRequest.getListPengajar().size());     
        }
        return tagDb.save(tag);
    }
}
