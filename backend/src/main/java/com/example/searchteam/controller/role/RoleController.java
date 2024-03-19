package com.example.searchteam.controller.role;

import com.example.searchteam.dto.request.role.RoleAddRequest;
import com.example.searchteam.dto.request.role.RoleRequest;
import com.example.searchteam.dto.response.role.RoleResponse;
import com.example.searchteam.service.role.RoleService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor

/**
 * Контроллер для роли
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Управление ролями",
                description = "Контроллер управления ролями", version = "1.0.0"
        )
)
public class RoleController {

    public static final String ROLE_GET_BY_ID = "/api/v1/role/get-by-id";
    public static final String ROLE_GET_ALL = "/api/v1/role/get-all";
    public static final String ROLE_EDIT = "/api/v1/role/edit";
    public static final String ROLE_DELETE = "/api/v1/role/delete";
    public static final String ROLE_ADD = "/api/v1/role/add";

    private final RoleService service;

    /**
     * Запрос получения роли по id
     * @param request - roleId
     * @return роль
     */
    @Operation(summary="Получение роли по ID")
    @PostMapping(
            value = ROLE_GET_BY_ID,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public RoleResponse getRoleById(@RequestBody RoleRequest request){
        return service.getRoleById(request);
    }

    /**
     * Запрос получения всех ролей
     * @return список ролей
     */
    @Operation(summary="Получение ролей")
    @PostMapping(
            value = ROLE_GET_ALL,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public List<RoleResponse> getAllRoles(){
        return service.getAllRoles();
    }

    /**
     * Запрос добавления роли
     * @param request - RoleAddRequest(id,name,roleType,description)
     * @return роль
     */
    @Operation(summary="Добавление роли")
    @PostMapping(
            value = ROLE_ADD,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public RoleResponse addRole(@RequestBody RoleAddRequest request) {
        return service.addRole(request);
    }

    /**
     * Запрос изменения роли
     * @param request - RoleAddRequest(id,name,roleType,description)
     * @return роль
     */
    @Operation(summary="Изменение роли")
    @PostMapping(
            value = ROLE_EDIT,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public RoleResponse editRole(@RequestBody RoleAddRequest request) {
        return service.editRole(request);
    }

    /**
     * Запрос удаления роли
     * @param request - RoleRequest(roleId)
     */
    @Operation(summary="Удаление роли")
    @PostMapping(
            value = ROLE_DELETE,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public void deleteRole(@RequestBody RoleRequest request) {
        service.deleteRole(request);
    }


}
