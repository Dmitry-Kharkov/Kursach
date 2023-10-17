package com.example.searchteam.controller.user;

import com.example.searchteam.dto.request.teamMember.TeamMemberRequest;
import com.example.searchteam.dto.request.user.UserRequest;
import com.example.searchteam.dto.response.teamMember.TeamMemberResponse;
import com.example.searchteam.dto.response.user.UserResponse;
import com.example.searchteam.service.teamMember.TeamMemberService;
import com.example.searchteam.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class UserController {

    public static final String USER_GET_BY_ID = "/api/v1/user/get-by-id";

    private final UserService service;

    @PostMapping(
            value = USER_GET_BY_ID,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public UserResponse getUserById(@RequestBody UserRequest request){
        return service.getUserById(request);
    }


}