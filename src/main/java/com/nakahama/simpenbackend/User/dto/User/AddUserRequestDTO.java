package com.nakahama.simpenbackend.User.dto.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequestDTO {
    String email;
    String role;
    String nama;
}
