package com.example.searchteam.mapper.role;

import com.example.searchteam.domain.role.RoleType;
import com.example.searchteam.dto.response.role.RoleTypeResponse;
import com.example.searchteam.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleTypeResponseMapper implements Mapper<RoleTypeResponse, RoleType> {
    @Override
    public RoleTypeResponse from(RoleType source) {
        return new RoleTypeResponse()
                .setRoleTypeId(source.getId())
                .setName(source.getName())
                .setDescription(source.getDescription())
                .setCreated(source.getCreatedDateTime())
                .setModified(source.getModifiedDateTime());
    }
}