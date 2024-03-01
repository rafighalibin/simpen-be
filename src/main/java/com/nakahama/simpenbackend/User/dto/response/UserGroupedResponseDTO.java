package com.nakahama.simpenbackend.User.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.nakahama.simpenbackend.User.model.UserModel;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupedResponseDTO {
    private List<CategoryUsers> roles;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CategoryUsers {
        private String role;
        private List<UserModel> users;
    }
}
