package com.example.searchteam.service.user;

import com.example.searchteam.dto.request.team.TeamRequest;
import com.example.searchteam.dto.request.user.UserRequest;
import com.example.searchteam.dto.response.team.TeamResponse;
import com.example.searchteam.dto.response.user.UserResponse;
import com.example.searchteam.service.domain.team.TeamDomainService;
import com.example.searchteam.service.domain.user.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDomainService service;

    public UserResponse getUserById(UserRequest request ){
        return service.getUserById(request.getUserId());
    }

}