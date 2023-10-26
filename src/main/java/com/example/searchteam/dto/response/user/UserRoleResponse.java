package com.example.searchteam.dto.response.user;

import com.example.searchteam.domain.role.Role;
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
public class UserRoleResponse {

    private Long userRoleId;
    private Long userId;
    private RoleResponse role;
    private LocalDateTime created;
    private LocalDateTime modified;

}
