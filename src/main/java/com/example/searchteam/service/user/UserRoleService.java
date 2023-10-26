package com.example.searchteam.service.user;

import com.example.searchteam.domain.user.UserRole;
import com.example.searchteam.dto.request.applicant.ApplicantAddRequest;
import com.example.searchteam.dto.request.team.TeamRequest;
import com.example.searchteam.dto.request.user.UserAddRequest;
import com.example.searchteam.dto.request.user.UserRequest;
import com.example.searchteam.dto.request.user.UserRoleRequest;
import com.example.searchteam.dto.response.applicant.ApplicantResponse;
import com.example.searchteam.dto.response.team.TeamResponse;
import com.example.searchteam.dto.response.user.UserResponse;
import com.example.searchteam.dto.response.user.UserRoleResponse;
import com.example.searchteam.service.domain.team.TeamDomainService;
import com.example.searchteam.service.domain.user.UserDomainService;
import com.example.searchteam.service.domain.user.UserRoleDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleDomainService service;

    public List<UserRole> getUserRolesByUserId(UserRoleRequest request ){
        return service.getUserRoleByUserId(request.getUserId());
    }
}