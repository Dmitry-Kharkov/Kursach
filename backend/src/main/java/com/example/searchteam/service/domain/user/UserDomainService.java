package com.example.searchteam.service.domain.user;

import com.example.searchteam.domain.role.Role;
import com.example.searchteam.domain.user.User;
import com.example.searchteam.domain.user.UserRole;
import com.example.searchteam.dto.request.user.LoginUserRequest;
import com.example.searchteam.dto.request.user.ResetPasswordRequest;
import com.example.searchteam.dto.request.user.UserAddRequest;
import com.example.searchteam.dto.request.user.UserEditPasswordRequest;
import com.example.searchteam.dto.request.user.UserEditRolesRequest;
import com.example.searchteam.dto.response.user.UserResponse;
import com.example.searchteam.mapper.user.UserLoginMapper;
import com.example.searchteam.mapper.user.UserMapper;
import com.example.searchteam.mapper.user.UserMerger;
import com.example.searchteam.mapper.user.UserResponseMapper;
import com.example.searchteam.repository.user.UserRepository;
import com.example.searchteam.repository.user.UserRoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDomainService {

    private final UserRepository repository;
    private final UserRoleRepository userRoleRepository;
    private final UserResponseMapper responseUserMapper;
    private final UserMapper userMapper;
    private final UserMerger userMerger;
    private final UserLoginMapper userLoginMapper;

    @Transactional
    public UserResponse getUserById(Long id) {
        return responseUserMapper.from(repository.findById(id).orElseThrow());
    }

    @Transactional
    public List<UserResponse> getAllUsers() {
        return responseUserMapper.from(repository.findAll());
    }

    @Transactional
    public List<User> getUserByFullName(String fullName) {
        return repository.getUserByFullName(fullName);
    }

    @Transactional
    public UserResponse getUserByLogin(String login) {
        return responseUserMapper.from(repository.findUserByLogin(login).orElse(new User()));
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

    @Transactional
    public Long editPasswordUser(UserEditPasswordRequest request) {
        var user = repository.getReferenceById(request.getId());
        return repository.save(user.setPassword(request.getPassword())).getId();
    }

    @Transactional
    public Long editRolesUser(UserEditRolesRequest request) {
        var userRoles = request.getRoles().stream()
                .map(r -> new UserRole().setUser(new User(request.getId())).setRole(new Role(r)))
                .toList();
        userRoleRepository.saveAll(userRoles);
        return repository.getReferenceById(request.getId()).getId();
    }


    @Transactional
    public void setUserRole(Long userId, List<Long> roles) {
        var userRoles = roles.stream()
                .map(r -> new UserRole().setUser(new User(userId)).setRole(new Role(r)))
                .toList();
        userRoleRepository.saveAll(userRoles);
    }

    @Transactional
    public void setUUIDByLogin(String login, UUID code) {
        var user = repository.getUserByLogin(login);
        user.setCode(code);
        repository.save(user);
    }

    @Transactional
    public Boolean isExists(LoginUserRequest request) {
        var loginUser = userLoginMapper.from(request);
        var users = repository.findAll();
        return !users.stream().filter(e -> e.getLogin().equals(loginUser.getLogin()) && e.getPassword().equals(loginUser.getPassword())).toList().isEmpty();

    }

    @Transactional
    public void resetPassword(ResetPasswordRequest request) {
        var user = repository.getUserByCode(request.getCode());
        user.setPassword(request.getPassword());
        user.setCode(null);
        repository.save(user);
    }
}