package com.example.searchteam.mapper.role;

import com.example.searchteam.domain.role.Role;
import com.example.searchteam.domain.role.RoleType;
import com.example.searchteam.dto.request.role.RoleAddRequest;
import com.example.searchteam.mapper.Merger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleMerger implements Merger<Role, RoleAddRequest> {

    @Override
    public Role merge(Role target, RoleAddRequest source) {
        return target
                .setName(source.getName())
                .setDescription(source.getDescription())
                .setRoleType(new RoleType(source.getRoleTypeId()));
    }
}