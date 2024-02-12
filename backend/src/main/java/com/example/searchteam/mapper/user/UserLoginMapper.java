package com.example.searchteam.mapper.user;

import com.example.searchteam.domain.user.User;
import com.example.searchteam.dto.request.user.LoginUserRequest;
import com.example.searchteam.dto.request.user.UserAddRequest;
import com.example.searchteam.mapper.Mapper;
import com.example.searchteam.repository.user.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginMapper implements Mapper<User, LoginUserRequest> {
    @Override
    public User from(LoginUserRequest source) {
        return new User()
                .setLogin(source.getLogin())
                .setPassword(source.getPassword());
    }

}