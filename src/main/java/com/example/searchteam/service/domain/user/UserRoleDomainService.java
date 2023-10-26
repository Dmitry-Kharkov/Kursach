package com.example.searchteam.service.domain.user;

import com.example.searchteam.domain.teamMember.TeamMember;
import com.example.searchteam.domain.user.User;
import com.example.searchteam.domain.user.UserRole;
import com.example.searchteam.dto.response.user.UserRoleResponse;
import com.example.searchteam.mapper.user.UserMapper;
import com.example.searchteam.mapper.user.UserMerger;
import com.example.searchteam.mapper.user.UserResponseMapper;
import com.example.searchteam.mapper.user.UserRoleResponseMapper;
import com.example.searchteam.repository.teamMember.TeamMemberRepository;
import com.example.searchteam.repository.user.UserRepository;
import com.example.searchteam.repository.user.UserRoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleDomainService {

    private final UserRoleRepository repository;
    private final UserRoleResponseMapper responseUserMapper;

    @Transactional
    public List<UserRole> getUserRoleByUserId(Long userId) {
        return repository.getUserRoleByUserId(userId);
    }

    @Transactional
    public List<UserRole> getUserRoleByRoleId(Long roleId) {
        return repository.getUserRoleByRoleId(roleId);
    }

}