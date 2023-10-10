package com.example.searchteam.repository.role;

import com.example.searchteam.domain.role.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleTypeRepository extends JpaRepository<RoleType, Long > {
    List<RoleType> getRoleTypeById(Long id);
    List<RoleType> getRoleTypeByName(String name);

    void deleteRoleTypeById(Long id);
    void deleteRoleTypeByName(String name);
}
