package com.example.searchteam.repository.team_member;

import com.example.searchteam.domain.team_member.TeamMemberType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamMemberTypeRepository extends JpaRepository<TeamMemberType, Long > {
    List<TeamMemberType> getTeamMemberTypeByName(String name);
    void deleteTeamMemberTypeByName(String name);
}