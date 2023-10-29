package com.example.searchteam.service.user;

import com.example.searchteam.dto.request.user.UserAddRequest;
import com.example.searchteam.dto.request.user.UserRequest;
import com.example.searchteam.dto.response.user.UserResponse;
import com.example.searchteam.service.domain.user.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDomainService service;

    public UserResponse getUserById(UserRequest request ){
        return service.getUserById(request.getUserId());
    }

    public List<UserResponse> getAllUsers(){
        return service.getAllUsers();
    }

    public UserResponse addUser(UserAddRequest request) {
        Long userId = service.addUser(request);
        service.setUserRole(userId, List.of(2L));
        return service.getUserById(userId);
    }


    public UserResponse editUser(UserAddRequest request) {
        Long userId = service.editUser(request);
        return service.getUserById(userId);
    }
}