package com.example.searchteam.service.team;

import com.example.searchteam.dto.request.team.TeamAddRequest;
import com.example.searchteam.dto.request.team.TeamFiltrationRequest;
import com.example.searchteam.dto.request.team.TeamRequest;
import com.example.searchteam.dto.response.team.ShortTeamResponse;
import com.example.searchteam.dto.response.team.TeamResponse;
import com.example.searchteam.service.domain.team.TeamDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamDomainService service;

    public TeamResponse getTeamById(TeamRequest request ){
        return service.getTeamById(request.getTeamId());
    }

    public List<TeamResponse> getAllTeams(){
        return service.getAllTeams();
    }


    public TeamResponse addTeam(TeamAddRequest request) {
        Long teamId = service.addTeam(request);
        return service.getTeamById(teamId);
    }

    public TeamResponse editTeam(TeamAddRequest request) {
        Long teamId = service.editTeam(request);
        return service.getTeamById(teamId);
    }

    public void deleteTeam(TeamRequest request) {
        service.deleteTeamById(request.getTeamId());
    }

    public List<ShortTeamResponse> getFilterTeams(TeamFiltrationRequest request){
        List<TeamResponse> teams=service.getAllTeams().stream()
                .filter(s-> request.getTeamTypes().contains(s.getTypeTeam()))
                .filter(s-> request.getIsCompleted()==(s.isCompleted()))
                .filter(s->request.getName().equals(s.getName()))
                .filter(s->request.getStart().isAfter(s.getCreated()))
                .filter(s->request.getFinish().isBefore(s.getCreated()))
                .filter(s->request.getUsers().contains(s.getUser()))
                .toList();
        return service.shortenedTeams(teams);
    }
}
