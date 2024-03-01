package com.nakahama.simpenbackend.User.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nakahama.simpenbackend.User.service.TagService;
import com.nakahama.simpenbackend.User.service.UserService;
import com.nakahama.simpenbackend.util.BaseResponse;


import com.nakahama.simpenbackend.User.dto.request.CreateTagRequestDTO;
import com.nakahama.simpenbackend.User.model.Pengajar;
import com.nakahama.simpenbackend.User.model.Tag;
import com.nakahama.simpenbackend.User.model.UserModel;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/tag")
public class TagController {
    
    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
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

    @PostMapping("/")
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
    public BaseResponse assignTag(@RequestBody CreateTagRequestDTO tagRequestDTO) {

        //get id tag request and assign it to variable tag
        Tag tag = tagService.getTagById(tagRequestDTO.getId());

          // cek apakah list pengajar kosong, jika kosong buat arrylist baru
        if (tag.getListPengajar() == null || tag.getListPengajar().isEmpty()){
        tag.setListPengajar(new ArrayList<Pengajar>());
        } 

        for (UUID idPengajar : tagRequestDTO.getListIdPengajar()) {
            UserModel userModel = userService.getUserById(idPengajar);
            Pengajar pengajar = userModel.getPengajar();
            tag.getListPengajar().add(pengajar);
        }
        tag.setJumlahPengajar(tag.getListPengajar().size());

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
        List<Tag> listAssignedTag = new ArrayList<Tag>();

        //check all tag if it has pengajar, if yes, add it to listAssignedTag
        for (Tag tag : listTag) {
            if (tag.getListPengajar() != null && !tag.getListPengajar().isEmpty()){
                listAssignedTag.add(tag);
            }
        }

        // make a handler for empty list
        if (listAssignedTag.isEmpty()){
            BaseResponse response = new BaseResponse();
            response.setCode(404);
            response.setStatus("Not Found");
            response.setMessage("Tag not found");
            response.setContent(listAssignedTag);
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
    public BaseResponse deleteUnassignTag(@RequestBody CreateTagRequestDTO tagRequestDTO) {
        Tag tag = tagService.getTagById(tagRequestDTO.getId());
        
        for(UUID idPengajar : tagRequestDTO.getListIdPengajar()){
            UserModel userModel = userService.getUserById(idPengajar);
            Pengajar pengajar = userModel.getPengajar();
            tag.getListPengajar().remove(pengajar);
        }
        tag.setListPengajar(tag.getListPengajar());
        tag.setJumlahPengajar(tag.getListPengajar().size());
        tagService.updateTag(tag);
        
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setStatus("OK");
        response.setMessage("Success");
        response.setContent(null);
        return response;
    }
}
