package com.example.searchteam.service.user;

import com.example.searchteam.dto.request.user.*;
import com.example.searchteam.dto.response.user.UserResponse;
import com.example.searchteam.service.domain.user.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if(!verificationPassword(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Некорректный пароль");
        }
        Long userId = service.addUser(request);
        service.setUserRole(userId, List.of(2L));
        return service.getUserById(userId);
    }


    public UserResponse editUser(UserAddRequest request) {
        Long userId = service.editUser(request);
        return service.getUserById(userId);
    }

    public UserResponse editPasswordUser(UserEditPasswordRequest request) {
        if(!verificationPassword(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Некорректный пароль");
        }
        Long userId = service.editPasswordUser(request);
        return service.getUserById(userId);
    }

    public UserResponse editRolesUser(UserEditRolesRequest request) {
        Long userId = service.editRolesUser(request);
        return service.getUserById(userId);
    }

    public Boolean isExists(LoginUserRequest request) {
        return service.isExists(request);
    }

    private boolean verificationPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
        Matcher m =  Pattern.compile(regex).matcher(password);
        return (m.matches());
    }
}