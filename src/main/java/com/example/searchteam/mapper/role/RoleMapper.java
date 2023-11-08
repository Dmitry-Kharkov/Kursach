package com.example.searchteam.mapper.role;

import com.example.searchteam.domain.role.Role;
import com.example.searchteam.dto.request.role.RoleAddRequest;
import com.example.searchteam.mapper.Mapper;
import com.example.searchteam.repository.role.RoleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleMapper implements Mapper<Role, RoleAddRequest> {

    private final RoleTypeRepository repository;
    @Override
    public Role from(RoleAddRequest source) {
        return new Role()
                .setName(source.getName())
                .setDescription(source.getDescription())
                .setRoleType(repository.getRoleTypeById(source.getRoleTypeId()));
    }
}
