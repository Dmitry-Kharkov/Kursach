package com.example.searchteam.repository.user;

import com.example.searchteam.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long > {
    List<User> getUserByFullName(String fullName);
    Optional <User> findUserByLogin(String login);
    void deleteUserByFullName(String fullName);
    void deleteUserByLogin(String login);

    User getUserByCode(UUID code);
    User getUserByLogin(String login);
}