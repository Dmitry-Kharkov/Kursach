package com.example.searchteam.dto.response.user;

import com.example.searchteam.dto.response.role.RoleResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class UserResponse {

    private Long userId;
    private String fullName;
    private String login;
    private String password;
    private List<RoleResponse> roles;
    private LocalDateTime created;
    private LocalDateTime modified;

}
