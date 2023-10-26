package com.example.searchteam.dto.request.user;

import com.example.searchteam.domain.role.Role;
import com.example.searchteam.domain.user.UserRole;
import com.example.searchteam.repository.user.UserRoleRepository;
import com.example.searchteam.service.domain.user.UserRoleDomainService;
import com.example.searchteam.service.user.UserRoleService;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
@Accessors(chain = true)

public class UserAddRequest {

    private Long id;
    private String name;
    private String login;
    private String password;
    private List<Role> roles;

}
