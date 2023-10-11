package com.example.searchteam.service.domain.role;

import com.example.searchteam.domain.role.Role;
import com.example.searchteam.repository.role.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RoleDomainService {

    private final RoleRepository repository;

    @Transactional
    public List<Role> getRoleByName(String name) {
        return repository.getRoleByName(name);
    }

    @Transactional
    public List<Role> getRoleByRoleTypeId(Long roleTypeId) {
        return repository.getRoleByRoleTypeId(roleTypeId);
    }

    @Transactional
    public void deleteRoleByName(String name) {
        repository.deleteRoleByName(name);
    }

}
