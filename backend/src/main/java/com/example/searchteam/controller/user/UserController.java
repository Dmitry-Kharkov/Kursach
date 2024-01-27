package com.example.searchteam.controller.user;

import com.example.searchteam.dto.request.user.*;
import com.example.searchteam.dto.response.user.UserResponse;
import com.example.searchteam.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class UserController {

    public static final String USER_GET_BY_ID = "/api/v1/user/get-by-id";
    public static final String USER_GET_ALL = "/api/v1/user/get-all";
    public static final String USER_EDIT = "/api/v1/user/edit";
    public static final String USER_EDIT_PASSWORD = "/api/v1/user/edit-pwd";
    public static final String USER_ADD = "/api/v1/user/add";
    public static final String USER_EDIT_ROLES="/api/v1/user/edit-roles";

    public static final String USER_LOGIN="/api/v1/user/login";

    private final UserService service;

    @PostMapping(
            value = USER_GET_BY_ID,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public UserResponse getUserById(@RequestBody UserRequest request){
        return service.getUserById(request);
    }

    @PostMapping(
            value = USER_ADD,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public UserResponse addUser(@RequestBody UserAddRequest request) {
        return service.addUser(request);
    }
    @PostMapping(
            value = USER_EDIT,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public UserResponse editUser(@RequestBody UserAddRequest request) {
        return service.editUser(request);
    }

    @PostMapping(
            value = USER_EDIT_PASSWORD,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public UserResponse editPasswordUser(@RequestBody UserEditPasswordRequest request) {
        return service.editPasswordUser(request);
    }

    @PostMapping(
            value = USER_EDIT_ROLES,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public UserResponse editRolesUser(@RequestBody UserEditRolesRequest request) {
        return service.editRolesUser(request);
    }

    @PostMapping(
            value = USER_GET_ALL,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public List<UserResponse> getAllUsers(){ return service.getAllUsers(); }

    @PostMapping(
            value = USER_LOGIN,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public Boolean isExists(@RequestBody LoginUserRequest request){ return service.isExists(request); }

}