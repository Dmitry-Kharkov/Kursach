package com.example.searchteam.service.role;

import com.example.searchteam.dto.request.role.RoleRequest;
import com.example.searchteam.dto.response.role.RoleResponse;
import com.example.searchteam.service.domain.role.RoleDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleDomainService service;

    public RoleResponse getRoleById(RoleRequest request ){
        return service.getRoleById(request.getRoleId());
    }

}
