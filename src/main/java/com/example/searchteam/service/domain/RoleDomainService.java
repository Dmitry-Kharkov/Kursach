package com.example.searchteam.service.domain;

import com.example.searchteam.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RoleDomainService {

    private final RoleRepository repository;


    @Transactional
    public void getRole(Long id) {


    }

    @Transactional
    public void deleteRoleById(Long id) {
        repository.deleteById(id);
    }

}
