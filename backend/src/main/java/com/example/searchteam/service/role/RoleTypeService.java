package com.example.searchteam.service.role;

import com.example.searchteam.dto.response.role.RoleTypeResponse;
import com.example.searchteam.service.domain.role.RoleTypeDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleTypeService {

    private final RoleTypeDomainService service;

    public List<RoleTypeResponse> getAllRoleTypes(){ return service.getAllRoleTypes(); }
}
