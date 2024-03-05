package com.example.searchteam.service.role;

import com.example.searchteam.domain.role.RoleType;
import com.example.searchteam.dto.request.role.RoleTypeAddRequest;
import com.example.searchteam.dto.response.role.RoleTypeResponse;
import com.example.searchteam.service.domain.role.RoleTypeDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

/**
 * Сервис типа роли
 * *Реализует методы обработки информации о типе роли
 */
public class RoleTypeService {

    /**
     * Domain Service типа роли
     * Реализует методы обработки информации о типе роли
     */
    private final RoleTypeDomainService service;

    /**
     * получение всех типов ролей
     * @return список типов ролей
     */
    public List<RoleTypeResponse> getAllRoleTypes(){ return service.getAllRoleTypes(); }

    /**
     * Изменение типа роли
     * @param request - RoleTypeAddRequest(id,name,description)
     * @return тип роли
     */
    public RoleTypeResponse editRoleType(RoleTypeAddRequest request){
        Long roleTypeId = service.editRoleType(request);
        return service.getRoleTypeById(roleTypeId);
    }

}
