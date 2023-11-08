package com.example.searchteam.controller.team;

import com.example.searchteam.dto.request.team.TeamAddRequest;
import com.example.searchteam.dto.request.team.TeamRequest;
import com.example.searchteam.dto.response.team.TeamResponse;
import com.example.searchteam.service.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class TeamController {

    public static final String TEAM_GET_BY_ID = "/api/v1/team/get-by-id";
    public static final String TEAM_GET_ALL = "/api/v1/team/get-all";
    public static final String TEAM_ADD = "/api/v1/team/add";
    public static final String TEAM_EDIT = "/api/v1/team/edit";
    public static final String TEAM_DELETE = "/api/v1/team/delete";


    private final TeamService service;

    @PostMapping(
            value = TEAM_GET_BY_ID,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public TeamResponse getTeamById(@RequestBody TeamRequest request){
        return service.getTeamById(request);
    }

    @PostMapping(
            value = TEAM_GET_ALL,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public List<TeamResponse> getAllTeams(){
        return service.getAllTeams();
    }

    @PostMapping(
            value = TEAM_ADD,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public TeamResponse addTeam(@RequestBody TeamAddRequest request) {
        return service.addTeam(request);
    }

    @PostMapping(
            value = TEAM_EDIT,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public TeamResponse editTeam(@RequestBody TeamAddRequest request) {
        return service.editTeam(request);
    }

    @PostMapping(
            value = TEAM_DELETE,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public void deleteTeam(@RequestBody TeamRequest request) {
        service.deleteTeam(request);
    }



}
