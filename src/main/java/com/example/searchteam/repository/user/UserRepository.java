package com.example.searchteam.repository.user;

import com.example.searchteam.domain.teamMember.TeamMemberType;
import com.example.searchteam.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long > {
    List<User> getUserById(Long id);
    List<User> getUserByFullName(String fullName);
    List<User> getUserByLogin(String login);

    void deleteUserById(Long id);
    void deleteUserByFullName(String fullName);
    void deleteUserByLogin(String login);
}