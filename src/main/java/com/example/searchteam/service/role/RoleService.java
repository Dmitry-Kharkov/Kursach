package com.example.searchteam.service.role;

import com.example.searchteam.dto.request.role.RoleAddRequest;
import com.example.searchteam.dto.request.role.RoleRequest;
import com.example.searchteam.dto.response.role.RoleResponse;
import com.example.searchteam.service.domain.role.RoleDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleDomainService service;

    public RoleResponse getRoleById(RoleRequest request ){
        return service.getRoleById(request.getRoleId());
    }

    public List<RoleResponse> getAllRoles(){ return service.getAllRoles(); }

    public RoleResponse addRole(RoleAddRequest request){
        Long roleId = service.addRole(request);
        return service.getRoleById(roleId);
    }

    public RoleResponse editRole(RoleAddRequest request) {
        Long roleId = service.editRole(request);
        return service.getRoleById(roleId);
    }

    public void deleteRole(RoleRequest request) {
        service.deleteRoleById(request.getRoleId());
    }
}
