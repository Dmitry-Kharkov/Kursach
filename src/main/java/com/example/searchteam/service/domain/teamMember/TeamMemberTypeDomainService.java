package com.example.searchteam.service.domain.teamMember;

import com.example.searchteam.domain.teamMember.TeamMemberType;
import com.example.searchteam.repository.teamMember.TeamMemberTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TeamMemberTypeDomainService {

    private final TeamMemberTypeRepository repository;


    @Transactional
    public List<TeamMemberType> getTeamMemberTypeById(Long id) {
        return repository.getTeamMemberTypeById(id);
    }

    @Transactional
    public List<TeamMemberType> getTeamMemberTypeByName(String name) {
        return repository.getTeamMemberTypeByName(name);
    }
    @Transactional
    public void deleteTeamMemberTypeById(Long id) {
        repository.deleteTeamMemberTypeById(id);
    }

    @Transactional
    public void deleteTeamMemberTypeByName(String name) {
        repository.deleteTeamMemberTypeByName(name);
    }

}