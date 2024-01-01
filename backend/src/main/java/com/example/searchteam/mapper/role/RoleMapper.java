package com.example.searchteam.mapper.role;

import com.example.searchteam.domain.role.Role;
import com.example.searchteam.domain.role.RoleType;
import com.example.searchteam.dto.request.role.RoleAddRequest;
import com.example.searchteam.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleMapper implements Mapper<Role, RoleAddRequest> {

    @Override
    public Role from(RoleAddRequest source) {
        return new Role()
                .setName(source.getName())
                .setDescription(source.getDescription())
                .setRoleType(new RoleType(source.getRoleTypeId()));
    }
}