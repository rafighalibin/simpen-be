package com.nakahama.simpenbackend.User.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nakahama.simpenbackend.User.service.TagService;
import com.nakahama.simpenbackend.User.service.UserService;
import com.nakahama.simpenbackend.util.ResponseUtil;

import jakarta.validation.Valid;

import com.nakahama.simpenbackend.User.dto.Tag.AssignTagRequestDTO;
import com.nakahama.simpenbackend.User.dto.Tag.AssignedTagResponse;
import com.nakahama.simpenbackend.User.dto.Tag.CreateTagRequest;
import com.nakahama.simpenbackend.User.dto.Tag.UpdateTagRequest;
import com.nakahama.simpenbackend.User.dto.Tag.mapper.AssignedTagMapper;
import com.nakahama.simpenbackend.User.model.Pengajar;
import com.nakahama.simpenbackend.User.model.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<Object> daftarTag() {
        List<Tag> listTag = tagService.getAllTag();
        return ResponseUtil.okResponse(listTag, "Success");
    }

    @PostMapping("")
    public ResponseEntity<Object> createTag(@Valid @RequestBody CreateTagRequest tagRequest) {
        Tag tag = tagService.createTag(tagRequest);
        return ResponseUtil.okResponse(tag, "Tag with name " + tagRequest.getNama() + " has been created");
    }

    @PutMapping("")
    public ResponseEntity<Object> updateTag(@Valid @RequestBody UpdateTagRequest tagRequest) {
        Tag tag = tagService.updateTag(tagRequest);
        return ResponseUtil.okResponse(tag, "Tag with name " + tagRequest.getNama() + " has been updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTag(@PathVariable(value = "id") Long id) {
        tagService.deleteTag(id);
        return ResponseUtil.okResponse(null, "Tag has been deleted");
    }

    @PostMapping("/assign")
    public ResponseEntity<Object> assignTag(@Valid @RequestBody AssignTagRequestDTO assignTagRequestDTO) {
        Pengajar pengajar = tagService.assignTag(assignTagRequestDTO);
        //Tag tag = tagService.assignTag(assignTagRequestDTO);
        return ResponseUtil.okResponse(null, "Success");
    }

    @GetMapping("/assign")
    public ResponseEntity<Object> getAssignedTags() {
        List<AssignedTagResponse> listAssignedTag = AssignedTagMapper.toDto(userService.retrieveAllUser());
        return ResponseUtil.okResponse(listAssignedTag, "Success");
    }

    @DeleteMapping("/assign")
    public ResponseEntity<Object> unassignTag(@RequestBody AssignTagRequestDTO unassignTagRequestDTO) {
        Pengajar pengajar = tagService.unassignTag(unassignTagRequestDTO);
        // Tag tag = tagService.assignTag(unassignTagRequestDTO);
        return ResponseUtil.okResponse(null, "Success");
    }
}
