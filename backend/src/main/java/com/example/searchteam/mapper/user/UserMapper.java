package com.example.searchteam.mapper.user;

import com.example.searchteam.domain.user.User;
import com.example.searchteam.dto.request.user.UserAddRequest;
import com.example.searchteam.mapper.Mapper;
import com.example.searchteam.repository.user.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper implements Mapper<User, UserAddRequest> {

    private final UserRoleRepository repository;
    @Override
    public User from(UserAddRequest source) {
        return new User()
                .setFullName(source.getName())
                .setLogin(source.getLogin())
                .setEmail(source.getEmail())
                .setPassword(source.getPassword());
    }
}
