package com.example.searchteam.repository.role;

import com.example.searchteam.domain.role.RoleType;
import com.example.searchteam.dto.response.role.RoleTypeResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleTypeRepository extends JpaRepository<RoleType, Long > {
    List<RoleType> getRoleTypeByName(String name);

    RoleType getRoleTypeById(Long id);

    void deleteRoleTypeByName(String name);
}
