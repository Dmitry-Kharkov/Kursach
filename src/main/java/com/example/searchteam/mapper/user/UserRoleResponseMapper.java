package com.example.searchteam.mapper.user;

import com.example.searchteam.domain.user.User;
import com.example.searchteam.domain.user.UserRole;
import com.example.searchteam.dto.response.user.UserResponse;
import com.example.searchteam.dto.response.user.UserRoleResponse;
import com.example.searchteam.mapper.Mapper;
import com.example.searchteam.mapper.role.RoleResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleResponseMapper implements Mapper<UserRoleResponse, UserRole> {
    private final RoleResponseMapper roleResponseMapper;
    @Override
    public UserRoleResponse from(UserRole source) {
        return new UserRoleResponse()
                .setUserRoleId(source.getId())
                .setUserId(source.getUser().getId())
                .setRole(roleResponseMapper.from(source.getRole()))
                .setCreated(source.getCreatedDateTime())
                .setModified(source.getModifiedDateTime());
    }
}