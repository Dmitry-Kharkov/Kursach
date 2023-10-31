package com.example.searchteam.service.domain.team_member;

import com.example.searchteam.domain.team_member.TeamMember;
import com.example.searchteam.dto.response.team_member.TeamMemberResponse;
import com.example.searchteam.mapper.team_member.TeamMemberResponseMapper;
import com.example.searchteam.repository.team_member.TeamMemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamMemberDomainService {

    private final TeamMemberRepository repository;
    private final TeamMemberResponseMapper responseTeamMemberMapper;

    @Transactional
    public TeamMemberResponse getTeamMemberById(Long id) {
        return responseTeamMemberMapper.from(repository.findById(id).orElseThrow());
    }

    @Transactional
    public List<TeamMember> getTeamMemberByName(String name) {
        return repository.getTeamMemberByName(name);
    }

    @Transactional
    public List<TeamMember> getTeamMemberByTeamMemberTypeId(Long teamMemberTypeid) {
        return repository.getTeamMemberByTeamMemberTypeId(teamMemberTypeid);
    }

    @Transactional
    public List<TeamMember> getTeamMemberByTeamId(Long teamId) {
        return repository.getTeamMemberByTeamId(teamId);
    }

    @Transactional
    public void deleteTeamMemberByName(String name) {
        repository.deleteTeamMemberByName(name);
    }

}