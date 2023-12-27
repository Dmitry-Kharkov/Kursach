package com.example.searchteam.controller.role;

import com.example.searchteam.dto.response.role.RoleTypeResponse;
import com.example.searchteam.service.role.RoleTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class RoleTypeController {
    public static final String ROLE_TYPE_GET_ALL = "/api/v1/role-type/get-all";

    private final RoleTypeService service;


    @PostMapping(
            value = ROLE_TYPE_GET_ALL,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public List<RoleTypeResponse> getAllRoleTypes(){
        return service.getAllRoleTypes();
    }

}
