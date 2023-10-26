package com.example.searchteam.mapper.user;

import com.example.searchteam.domain.user.User;
import com.example.searchteam.domain.user.UserRole;
import com.example.searchteam.dto.response.user.UserResponse;
import com.example.searchteam.mapper.Mapper;
import com.example.searchteam.mapper.role.RoleResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserResponseMapper implements Mapper<UserResponse, User> {

    private final RoleResponseMapper roleResponseMapper;

    @Override
    public UserResponse from(User source) {
        return new UserResponse()
                .setUserId(source.getId())
                .setFullName(source.getFullName())
                .setLogin(source.getLogin())
                .setPassword(source.getPassword())
                .setRoles(roleResponseMapper.from(source.getUserRoles().stream().map(UserRole::getRole).toList()))
                .setCreated(source.getCreatedDateTime())
                .setModified(source.getModifiedDateTime());
    }
}