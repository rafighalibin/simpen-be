package com.nakahama.simpenbackend.User.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nakahama.simpenbackend.User.service.TagService;
import com.nakahama.simpenbackend.util.ResponseUtil;

import jakarta.validation.Valid;

import com.nakahama.simpenbackend.User.dto.Tag.AssignTagRequestDTO;
import com.nakahama.simpenbackend.User.dto.Tag.AssignedTagResponse;
import com.nakahama.simpenbackend.User.dto.Tag.CreateTagRequest;
import com.nakahama.simpenbackend.User.dto.Tag.mapper.AssignedTagMapper;
import com.nakahama.simpenbackend.User.model.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public ResponseEntity<Object> daftarTag() {
        List<Tag> listTag = tagService.getAllTag();
        return ResponseUtil.okResponse(listTag, "Success");
    }

    @PostMapping("/")
    public ResponseEntity<Object> createTag(@Valid @RequestBody CreateTagRequest tagRequest) {
        Tag tag = tagService.createTag(tagRequest);
        return ResponseUtil.okResponse(tag, "Tag with name " + tagRequest.getNama() + " has been created");
    }

    @PostMapping("/assign")
    public ResponseEntity<Object> assignTag(@Valid @RequestBody AssignTagRequestDTO assignTagRequestDTO) {
        Tag tag = tagService.assignTag(assignTagRequestDTO);
        return ResponseUtil.okResponse(null, "Tag with name " + tag.getNama() + " has been assigned");
    }

    @GetMapping("/assign")
    public ResponseEntity<Object> getAssignedTags() {
        List<AssignedTagResponse> listAssignedTag = AssignedTagMapper.toDto(tagService.getAllTag());
        return ResponseUtil.okResponse(listAssignedTag, "Success");
    }

    @DeleteMapping("/assign")
    public ResponseEntity<Object> unassignTag(@RequestBody AssignTagRequestDTO unassignTagRequestDTO) {
        Tag tag = tagService.unassignTag(unassignTagRequestDTO);
        return ResponseUtil.okResponse(null, "Tag with name " + tag.getNama() + " has been unassigned");
    }
}
