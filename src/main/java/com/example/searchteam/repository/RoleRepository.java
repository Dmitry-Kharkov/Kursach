package com.example.searchteam.repository;

import com.example.searchteam.domain.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long > {

    List<Role> getRoleByCreatedDateTimeIsBefore(LocalDateTime created);


    @Query(nativeQuery = true,
            value = "SELECT * FROM REPOSITORY.ROLE R " +
                    "JOIN REPOSITORY.ROLE_TYPE RT on RT.ROLE_TYPE_ID = R.ROLE_TYPE_ID " +
                    "WHERE CREATED > :created  AND RT.CREATED > :created")
    List<Role> getRoleByCreatedDateTimeIsBefore2(@Param("created") LocalDateTime created);

}
