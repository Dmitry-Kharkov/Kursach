package com.example.searchteam.controller.role;

import com.example.searchteam.dto.request.role.RoleAddRequest;
import com.example.searchteam.dto.request.role.RoleTypeAddRequest;
import com.example.searchteam.dto.response.role.RoleTypeResponse;
import com.example.searchteam.service.role.RoleTypeService;
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
 * Контроллер для типа роли
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Управление типами ролей",
                description = "Контроллер управления типами ролей", version = "1.0.0"
        )
)
public class RoleTypeController {
    public static final String ROLE_TYPE_GET_ALL = "/api/v1/role-type/get-all";
    public static final String ROLE_TYPE_EDIT = "/api/v1/role-type/edit";

    private final RoleTypeService service;

    /**
     * Запрос получения всех типов ролей
     * @return типов ролей
     */
    @Operation(summary="Получение всех типов ролей")
    @PostMapping(
            value = ROLE_TYPE_GET_ALL,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public List<RoleTypeResponse> getAllRoleTypes(){
        return service.getAllRoleTypes();
    }

    /**
     * Запрос изменения типа роли
     * @param request - RoleTypeAddRequest(id,name,description)
     * @return тип роли
     */
    @Operation(summary="Изменение типа роли")
    @PostMapping(
            value = ROLE_TYPE_EDIT,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public RoleTypeResponse editRoleType(@RequestBody RoleTypeAddRequest request){
        return service.editRoleType(request);
    }
}
