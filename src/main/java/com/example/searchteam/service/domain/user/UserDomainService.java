package com.example.searchteam.service.domain.user;

import com.example.searchteam.domain.teamMember.TeamMember;
import com.example.searchteam.domain.user.User;
import com.example.searchteam.dto.response.team.TeamResponse;
import com.example.searchteam.dto.response.user.UserResponse;
import com.example.searchteam.mapper.team.TeamResponseMapper;
import com.example.searchteam.mapper.user.UserResponseMapper;
import com.example.searchteam.repository.teamMember.TeamMemberRepository;
import com.example.searchteam.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDomainService {

    private final UserRepository repository;
    private final UserResponseMapper responseUserMapper;

    @Transactional
    public UserResponse getUserById(Long id) {
        return responseUserMapper.from(repository.findById(id).orElseThrow());
    }
    @Transactional
    public List<User> getUserByFullName(String fullName) {
        return repository.getUserByFullName(fullName);
    }

    @Transactional
    public List<User> getUserByLogin(String login) {
        return repository.getUserByLogin(login);
    }

    @Transactional
    public void deleteUserByFullName(String fullName) {
        repository.deleteUserByFullName(fullName);
    }

    @Transactional
    public void deleteUserByLogin(String login) {
        repository.deleteUserByLogin(login);
    }



}