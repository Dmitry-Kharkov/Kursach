package com.example.searchteam.service.user;

import com.example.searchteam.domain.user.UserRole;
import com.example.searchteam.dto.request.user.UserRoleRequest;
import com.example.searchteam.service.domain.user.UserRoleDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

/**
 * Сервис роли пользователя
 * @deprecated реализует методы обработки информации о роли пользователя
 */
public class UserRoleService {

    /**
     * Domain Service роли пользователя
     * @deprecated реализует методы обработки информации о роли пользователя
     */
    private final UserRoleDomainService service;

    /**
     * получение роли пользователя по id
     * @param request - id
     */
    public List<UserRole> getUserRolesByUserId(UserRoleRequest request) {
        return service.getUserRoleByUserId(request.getUserId());
    }
}