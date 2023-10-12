package com.example.searchteam.repository.role;

import com.example.searchteam.domain.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long > {
    List<Role> getRoleByName(String name);
    List<Role> getRoleByRoleTypeId(Long roleTypeId);
    void deleteRoleByName(String name);




/*    @Query(nativeQuery = true,
            value = "SELECT * FROM REPOSITORY.ROLE R " +
                    "JOIN REPOSITORY.ROLE_TYPE RT on RT.ROLE_TYPE_ID = R.ROLE_TYPE_ID " +
                    "WHERE CREATED > :created  AND RT.CREATED > :created")
    List<Role> getRoleByCreatedDateTimeIsBefore2(@Param("created") LocalDateTime created);*/

}
