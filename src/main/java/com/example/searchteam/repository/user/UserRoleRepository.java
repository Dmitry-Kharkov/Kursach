package com.example.searchteam.repository.user;

import com.example.searchteam.domain.user.User;
import com.example.searchteam.domain.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long > {
    List<UserRole> getUserRoleById(Long id);
    List<UserRole> getUserRoleByUserId(Long userId);
    List<UserRole> getUserRoleByRoleId(Long roleId);

    void deleteUserRoleById(Long Id);
}