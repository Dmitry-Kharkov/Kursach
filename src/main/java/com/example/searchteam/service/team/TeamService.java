package com.example.searchteam.service.team;

import com.example.searchteam.dto.request.team.TeamRequest;
import com.example.searchteam.dto.response.team.TeamResponse;
import com.example.searchteam.service.domain.team.TeamDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamDomainService service;

    public TeamResponse getTeamById(TeamRequest request ){
        return service.getTeamById(request.getTeamId());
    }

}