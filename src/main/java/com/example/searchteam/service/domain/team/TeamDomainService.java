package com.example.searchteam.service.domain.team;
import com.example.searchteam.domain.team.Team;
import com.example.searchteam.repository.team.TeamRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TeamDomainService {

    private final TeamRepository repository;


    @Transactional
    public List<Team> getTeamById(Long id) {
        return repository.getTeamById(id);
    }

    @Transactional
    public List<Team> getTeamByName(String name) {
        return repository.getTeamByName(name);
    }

    @Transactional
    public List<Team> getTeamByUserId(Long userId) {
        return repository.getTeamByUserId(userId);
    }

    @Transactional
    public List<Team> getTeamByTeamTypeId(Long teamTypeId) {
        return repository.getTeamByUserId(teamTypeId);
    }

    @Transactional
    public void deleteTeamById(Long id) {
        repository.deleteTeamById(id);
    }

    @Transactional
    public void deleteTeamByName(String name) {
        repository.deleteTeamByName(name);
    }

}
