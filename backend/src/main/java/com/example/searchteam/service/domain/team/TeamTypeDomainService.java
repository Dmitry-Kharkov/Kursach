package com.example.searchteam.service.domain.team;

import com.example.searchteam.domain.team.Team;
import com.example.searchteam.domain.team.TeamType;
import com.example.searchteam.repository.team.TeamTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamTypeDomainService {

    private final TeamTypeRepository repository;

    @Transactional
    public List<TeamType> getTeamTypeByName(String name) {
        return repository.getTeamTypeByName(name);
    }

    @Transactional
    public void deleteTeamTypeByName(String name) {
        repository.deleteTeamTypeByName(name);
    }

}