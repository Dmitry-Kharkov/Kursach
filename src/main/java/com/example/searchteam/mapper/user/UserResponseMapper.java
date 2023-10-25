package com.example.searchteam.mapper.user;

import com.example.searchteam.domain.user.User;
import com.example.searchteam.dto.response.user.UserResponse;
import com.example.searchteam.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserResponseMapper implements Mapper<UserResponse, User> {
    @Override
    public UserResponse from(User source) {
        return new UserResponse()
                .setUserId(source.getId())
                .setFullName(source.getFullName())
                .setLogin(source.getLogin())
                .setPassword(source.getPassword())
                .setCreated(source.getCreatedDateTime())
                .setModified(source.getModifiedDateTime());
    }
}