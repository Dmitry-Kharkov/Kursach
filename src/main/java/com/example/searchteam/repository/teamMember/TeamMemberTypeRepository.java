package com.example.searchteam.repository.teamMember;

import com.example.searchteam.domain.teamMember.TeamMemberType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamMemberTypeRepository extends JpaRepository<TeamMemberType, Long > {
    List<TeamMemberType> getTeamMemberTypeById(Long id);
    List<TeamMemberType> getTeamMemberTypeByName(String name);

    void deleteTeamMemberTypeById(Long Id);
    void deleteTeamMemberTypeByName(String name);
}