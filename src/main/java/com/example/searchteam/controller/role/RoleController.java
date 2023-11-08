package com.example.searchteam.controller.role;

import com.example.searchteam.dto.request.role.RoleRequest;
import com.example.searchteam.dto.response.role.RoleResponse;
import com.example.searchteam.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class RoleController {

    public static final String ROLE_GET_BY_ID = "/api/v1/role/get-by-id";
    public static final String ROLE_GET_ALL = "/api/v1/role/get-all";

    private final RoleService service;

    @PostMapping(
            value = ROLE_GET_BY_ID,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public RoleResponse getRoleById(@RequestBody RoleRequest request){
        return service.getRoleById(request);
    }

    @PostMapping(
            value = ROLE_GET_ALL,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public List<RoleResponse> getAllRoles(){
        return service.getAllRoles();
    }


}
