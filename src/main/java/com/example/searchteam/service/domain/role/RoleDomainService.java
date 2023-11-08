package com.example.searchteam.service.domain.role;

import com.example.searchteam.dto.request.role.RoleAddRequest;
import com.example.searchteam.dto.response.role.RoleResponse;
import com.example.searchteam.mapper.role.RoleMapper;
import com.example.searchteam.mapper.role.RoleMerger;
import com.example.searchteam.mapper.role.RoleResponseMapper;
import com.example.searchteam.repository.role.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RoleDomainService {

    private final RoleRepository repository;

    private final RoleResponseMapper responseRoleMapper;
    private final RoleMapper roleMapper;
    private final RoleMerger roleMerger;


    @Transactional
    public RoleResponse getRoleById(Long id) {
        return responseRoleMapper.from(repository.findById(id).orElseThrow());
    }

    @Transactional
    public List<RoleResponse> getAllRoles() {
        return responseRoleMapper.from(repository.findAll());
    }

    @Transactional
    public Long addRole(RoleAddRequest request) {

        var role = roleMapper.from(request);
        return repository.save(role).getId();
    }

    @Transactional
    public Long editRole(RoleAddRequest request) {
        var role = repository.getReferenceById(request.getId());
        return repository.save(roleMerger.merge(role, request)).getId();
    }

    @Transactional
    public List<RoleResponse> getRoleByName(String name) {
        return responseRoleMapper.from(repository.getRoleByName(name));
    }

    @Transactional
    public List<RoleResponse> getRoleByRoleTypeId(Long roleTypeId) {
        return responseRoleMapper.from(repository.getRoleByRoleTypeId(roleTypeId));
    }

    @Transactional
    public void deleteRoleById(Long id) {
        repository.deleteById(id);
    }


    @Transactional
    public void deleteRoleByName(String name) {
        repository.deleteRoleByName(name);
    }

}
