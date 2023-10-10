package com.example.searchteam.service.domain.role;

import com.example.searchteam.domain.role.RoleType;
import com.example.searchteam.repository.role.RoleTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleTypeDomainService {

    private final RoleTypeRepository repository;


    @Transactional
    public List<RoleType> getRoleTypeById(Long id) {
        return repository.getRoleTypeById(id);
    }

    @Transactional
    public List<RoleType> getRoleTypeByName(String name) {
        return repository.getRoleTypeByName(name);
    }

    @Transactional
    public void deleteRoleTypeById(Long id) {
        repository.deleteRoleTypeById(id);
    }

    @Transactional
    public void deleteRoleTypeByName(String name) {
        repository.deleteRoleTypeByName(name);
    }

}
