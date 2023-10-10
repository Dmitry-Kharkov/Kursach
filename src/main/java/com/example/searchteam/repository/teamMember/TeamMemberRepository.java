package com.example.searchteam.repository.teamMember;

import com.example.searchteam.domain.teamMember.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long > {
    List<TeamMember> getTeamMemberById(Long id);
    List<TeamMember> getTeamMemberByName(String name);
    List<TeamMember> getTeamMemberByTeamMemberTypeId(Long teamMemberTypeId);
    List<TeamMember> getTeamMemberByTeamId(Long teamId);

    void deleteTeamMemberById(Long Id);
    void deleteTeamMemberByName(String name);
}