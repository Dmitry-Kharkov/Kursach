package com.example.searchteam.mapper.role;

import com.example.searchteam.domain.role.Role;
import com.example.searchteam.dto.response.role.RoleResponse;
import com.example.searchteam.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleResponseMapper implements Mapper<RoleResponse, Role> {
    @Override
    public RoleResponse from(Role source) {
         return new RoleResponse()
                .setRoleId(source.getId())
                .setName(source.getName())
                .setDescription(source.getDescription())
                .setCreated(source.getCreatedDateTime())
                .setModified(source.getModifiedDateTime());
    }
}
