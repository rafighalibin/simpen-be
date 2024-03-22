package com.nakahama.simpenbackend.User.dto.User;

import java.util.List;
import java.util.UUID;
import com.nakahama.simpenbackend.User.dto.Tag.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserWithTagsResponseDTO {
    private UUID id;
    private String nama;
    private String email;
    private String emailPribadi;
    private String jenisKelamin;
    private String role;
    private List<ReadTagResponseDTO> tags = null;
}
