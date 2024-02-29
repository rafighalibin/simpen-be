package com.nakahama.simpenbackend.User.dto.request;

import java.util.List;

import com.nakahama.simpenbackend.User.model.Pengajar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateTagRequestDTO {
    private String nama;

    private List<Pengajar> listPengajar;
}
