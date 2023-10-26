package com.example.searchteam.service.domain.user;

import com.example.searchteam.domain.user.User;
import com.example.searchteam.dto.request.user.UserAddRequest;
import com.example.searchteam.dto.response.user.UserResponse;
import com.example.searchteam.mapper.user.UserMapper;
import com.example.searchteam.mapper.user.UserMerger;
import com.example.searchteam.mapper.user.UserResponseMapper;
import com.example.searchteam.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDomainService {

    private final UserRepository repository;
    private final UserResponseMapper responseUserMapper;
    private final UserMapper userMapper;
    private final UserMerger userMerger;

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

    @Transactional
    public Long addUser(UserAddRequest request) {

        var user = userMapper.from(request);

        user.setUserRoles(Collections.emptyList());

        return repository.save(user).getId();
    }

    @Transactional
    public Long editUser(UserAddRequest request) {
        var user = repository.getReferenceById(request.getId());
        return repository.save(userMerger.merge(user, request)).getId();
    }

}