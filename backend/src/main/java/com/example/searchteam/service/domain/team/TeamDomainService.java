package com.example.searchteam.service.domain.team;

import com.example.searchteam.domain.team.Team;
import com.example.searchteam.dto.request.team.TeamAddRequest;
import com.example.searchteam.dto.response.team.TeamResponse;
import com.example.searchteam.mapper.team.TeamMapper;
import com.example.searchteam.mapper.team.TeamMerger;
import com.example.searchteam.mapper.team.TeamResponseMapper;
import com.example.searchteam.repository.team.TeamRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TeamDomainService {

    private final TeamRepository repository;
    private final TeamResponseMapper responseTeamMapper;
    private final TeamMapper teamMapper;
    private final TeamMerger teamMerger;

    @Transactional
    public TeamResponse getTeamById(Long id) {
        return responseTeamMapper.from(repository.findById(id).orElseThrow());
    }

    @Transactional
    public List<TeamResponse> getAllTeams() {
        return responseTeamMapper.from(repository.findAll());
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
    public void deleteTeamByName(String name) {
        repository.deleteTeamByName(name);
    }

    @Transactional
    public Long addTeam(TeamAddRequest request) {
        return repository.save(teamMapper.from(request)).getId();
    }

    @Transactional
    public Long editTeam(TeamAddRequest request) {
        var team = repository.getReferenceById(request.getId());
        return repository.save(teamMerger.merge(team, request)).getId();
    }

    @Transactional
    public void deleteTeamById(Long id) {
        repository.deleteById(id);
    }


}
