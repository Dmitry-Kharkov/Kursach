package com.example.searchteam.service.role;

import com.example.searchteam.dto.request.role.RoleAddRequest;
import com.example.searchteam.dto.request.role.RoleRequest;
import com.example.searchteam.dto.response.role.RoleResponse;
import com.example.searchteam.service.domain.role.RoleDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

/**
 * Сервис роли
 * @deprecated реализует методы обработки информации о роли
 */
public class RoleService {

    /**
     * Domain Service роли
     * @deprecated реализует методы обработки информации о роли
     */
    private final RoleDomainService service;

    /**
     * получение роли по id
     * @param request - id
     */
    public RoleResponse getRoleById(RoleRequest request ){
        return service.getRoleById(request.getRoleId());
    }

    /**
     * получение всех ролей
     */
    public List<RoleResponse> getAllRoles(){ return service.getAllRoles(); }

    /**
     * Создание новой роли
     * @param request - RoleAddRequest(id,name,roleType,description)
     */
    public RoleResponse addRole(RoleAddRequest request){
        Long roleId = service.addRole(request);
        return service.getRoleById(roleId);
    }

    /**
     * Изменение роли
     * @param request - RoleAddRequest(id,name,roleType,description)
     */
    public RoleResponse editRole(RoleAddRequest request) {
        Long roleId = service.editRole(request);
        return service.getRoleById(roleId);
    }

    /**
     * Удаление заявки
     * @param request - RoleRequest(roleId)
     */
    public void deleteRole(RoleRequest request) {
        service.deleteRoleById(request.getRoleId());
    }
}
