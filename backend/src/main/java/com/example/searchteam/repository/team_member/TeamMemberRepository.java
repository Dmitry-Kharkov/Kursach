package com.example.searchteam.repository.team_member;

import com.example.searchteam.domain.team_member.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long > {
    List<TeamMember> getTeamMemberByName(String name);
    List<TeamMember> getTeamMemberByTeamMemberTypeId(Long teamMemberTypeId);
    List<TeamMember> getTeamMemberByTeamId(Long teamId);
    void deleteTeamMemberByName(String name);
}