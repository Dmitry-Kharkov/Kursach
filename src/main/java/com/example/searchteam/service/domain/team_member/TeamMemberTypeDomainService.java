package com.example.searchteam.service.domain.team_member;

import com.example.searchteam.domain.team_member.TeamMemberType;
import com.example.searchteam.repository.team_member.TeamMemberTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TeamMemberTypeDomainService {

    private final TeamMemberTypeRepository repository;

    @Transactional
    public List<TeamMemberType> getTeamMemberTypeByName(String name) {
        return repository.getTeamMemberTypeByName(name);
    }

    @Transactional
    public void deleteTeamMemberTypeByName(String name) {
        repository.deleteTeamMemberTypeByName(name);
    }

}