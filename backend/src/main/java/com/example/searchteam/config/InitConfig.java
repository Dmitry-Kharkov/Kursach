package com.example.searchteam.config;

import com.example.searchteam.dto.request.user.UserAddRequest;
import com.example.searchteam.service.domain.user.UserDomainService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitConfig {

    private final UserDomainService userDomainService;
    private final PasswordEncoder passwordEncoder;

    @Value("${search_team.admin.login}")
    private String login;

    @Value("${search_team.admin.pwd}")
    private String password;


    @PostConstruct
    public void initAdmin(){
        if (userDomainService.getUserByLogin(login).getId() == null){
           var user =  userDomainService.addUser(
                   new UserAddRequest()
                           .setName("admin")
                           .setLogin(login)
                           .setPassword(passwordEncoder.encode(password)));
           userDomainService.setUserRole(user, List.of(0L));
        }
    }

}
