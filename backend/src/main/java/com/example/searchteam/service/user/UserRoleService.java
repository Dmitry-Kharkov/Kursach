package com.example.searchteam.service.user;

import com.example.searchteam.domain.user.UserRole;
import com.example.searchteam.dto.request.user.UserRoleRequest;
import com.example.searchteam.service.domain.user.UserRoleDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleDomainService service;

    public List<UserRole> getUserRolesByUserId(UserRoleRequest request) {
        return service.getUserRoleByUserId(request.getUserId());
    }
}