package com.nakahama.simpenbackend.User.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nakahama.simpenbackend.User.service.TagService;
import com.nakahama.simpenbackend.util.BaseResponse;
import com.nakahama.simpenbackend.User.model.Pengajar;
import com.nakahama.simpenbackend.User.model.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/tag")
public class TagController {
    
    @Autowired
    private TagService tagService;

    @GetMapping("")
    public BaseResponse daftarTag(Model model) {
        List<Tag> listTag = tagService.getAllTag();

        // make a handler for empty list
        if (listTag.isEmpty() ){
            BaseResponse response = new BaseResponse();
            response.setCode(404);
            response.setStatus("Not Found");
            response.setMessage("Tag not found");
            response.setContent(listTag);
            return response;
        }

        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setStatus("OK");
        response.setMessage("Success");
        response.setContent(listTag);

        return response;
    }

    @PostMapping("")
    public BaseResponse createTag(@RequestBody Tag tagRequest) {
        
        // check if tag already exist
        if (tagService.isNamaExist(tagRequest.getNama())){
            BaseResponse response = new BaseResponse();
            response.setCode(400);
            response.setStatus("Bad Request");
            response.setMessage("Tag already exist");
            response.setContent(tagRequest);
            return response;
        }
        Tag tag = tagService.createTag(tagRequest);

        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setStatus("OK");
        response.setMessage("Success");
        response.setContent(tag);
        return response;
    }

    @PostMapping("/assign")
    public BaseResponse assignTag(@RequestBody Tag tagRequest) {

        //get id tag request and assign it to variable tag
        Tag tag = tagService.getTagById(tagRequest.getId());

          // cek apakah list pengajar kosong, jika kosong buat arrylist baru
          if (tagRequest.getListPengajar() == null || tagRequest.getListPengajar().isEmpty()){
            tag.setListPengajar(new ArrayList<Pengajar>());
        } 
        tag.getListPengajar().add(new Pengajar());
        tag.setJumlahPengajar(tagRequest.getListPengajar().size());

        tagService.updateTag(tag);

        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setStatus("OK");
        response.setMessage("Success");
        response.setContent(tag);
        return response;
    }

    @GetMapping("/assign")
    public BaseResponse daftarAssignedTag(Model model) {
        List<Tag> listTag = tagService.getAllTag();
        var listAssignedTag = new ArrayList<Tag>();

        //check all tag if it has pengajar, if yes, add it to listAssignedTag
        for (Tag tag : listTag) {
            if (tag.getListPengajar() != null && tag.getListPengajar().isEmpty()){
                listAssignedTag.add(tag);
            }
        }

        // make a handler for empty list
        if (listAssignedTag.isEmpty()){
            BaseResponse response = new BaseResponse();
            response.setCode(404);
            response.setStatus("Not Found");
            response.setMessage("Tag not found");
            response.setContent(listTag);
            return response;
        }

        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setStatus("OK");
        response.setMessage("Success");
        response.setContent(listAssignedTag);

        return response;
    }

    @DeleteMapping("/assign")
    public BaseResponse deleteUnassignTag(@RequestParam Tag tagRequest) {
        var tag = tagService.getTagById(tagRequest.getId());
        var pengajarAssigned = tag.getListPengajar();
        
        var pengajarToUnassign = tagRequest.getListPengajar();
        for (Pengajar pengajar : pengajarToUnassign) {
            pengajarAssigned.remove(pengajar);
        }
        tag.setListPengajar(pengajarAssigned);
        tag.setJumlahPengajar(pengajarAssigned.size());
        tagService.updateTag(tag);
        
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setStatus("OK");
        response.setMessage("Success");
        response.setContent(null);
        return response;
    }
}
